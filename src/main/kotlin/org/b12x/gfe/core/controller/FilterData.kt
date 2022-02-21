package org.b12x.gfe.core.controller

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.lang.Math.floor
import kotlin.math.truncate

object FilterData {

    /**
     * Scans for duplicate entries in nested lists.
     *
     * @params List<List<String>> of GFEs and traditional names.
     * @return a list of duplicates, both GFE and traditional names.
     */
    fun scanForDuplicates(list: List<List<String>>): List<List<String>> {
        var listToManipulate = list
        var listOfDuplicates = ArrayList<List<String>>()


        // cycling through the list of GFE/name pairs to check for duplicates
        while (listToManipulate.isNotEmpty()) {
            // first GFE/name pair
            var listToTest = list.first()

            // the rest of the GFE/name pairs
            var listToManipulate = list.drop(1)

            // flatten the rest of the list to search
            var testingList = listToManipulate.flatten()

            // are either the GFE or name found in the rest of the list?
            // if yes, what are their index in the testing list?
            var indexOfDuplicateGfe = testingList.indexOf(listToTest[0])
            var indexOfDuplicateName = testingList.indexOf(listToTest[1])

            // if a duplicate is found, the first GFE/name pair
            // (the ones to be matched) is added to the duplicate list.
            if ((indexOfDuplicateGfe > -1) or (indexOfDuplicateName > -1)) {
                listOfDuplicates.add(listToTest)
            }

            if ((indexOfDuplicateGfe >= 2) or ((indexOfDuplicateName - 1) >= 2)) {
                indexOfDuplicateGfe /= 2
            } else if ((indexOfDuplicateGfe >= 0) or (indexOfDuplicateName >= 0)) {
                listOfDuplicates.add(listToTest)
                listOfDuplicates.add(listToManipulate[indexOfDuplicateGfe])
            }

            listOfDuplicates.add(listToTest)
            listOfDuplicates.add(listToManipulate[indexOfDuplicateGfe])


        }
//        list.forEach()
        return listOfDuplicates.toList()
    }

    /**
     * Reads data from a csv file.
     * The data is returned in a parent list containing
     * sublists of strings, one list per row. It removes the first row.
     *
     * @params a csv file
     * @return a list of list<String>'s>
     */
    fun importData(file: File): List<List<String>> {
        val incomingData = csvReader().readAll(file)
        return incomingData.drop(1)
    }
}