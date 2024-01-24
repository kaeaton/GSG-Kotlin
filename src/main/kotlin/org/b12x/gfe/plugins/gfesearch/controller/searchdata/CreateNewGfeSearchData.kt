package org.b12x.gfe.plugins.gfesearch.controller.searchdata

import org.b12x.gfe.core.model.GetDataFiles
import org.b12x.gfe.plugins.gfesearch.controller.locistategfesearch.LociStateContextGfeSearch
import org.b12x.gfe.plugins.gfesearch.model.regex.BuildHeaderString
import org.b12x.gfe.plugins.gfesearch.model.regex.BuildRegexString
import org.b12x.gfe.plugins.gfesearch.view.GfeCheckboxPrint
import org.b12x.gfe.plugins.gfesearch.view.GfeRadioFileType
import org.b12x.gfe.plugins.gfesearch.view.GfeTextAreaInfo
import org.b12x.gfe.plugins.gfesearch.view.GfeViewMethods
import org.b12x.gfe.plugins.namesearch.view.NameTextAreaInfo
import java.io.File
import tornadofx.*

object CreateNewGfeSearchData {

    private val stateContext = LociStateContextGfeSearch
    val gfeInformationTextArea = find(GfeTextAreaInfo::class)
    val nameInformationTextArea = find(NameTextAreaInfo::class)


    fun generateSearchData(): GfeSearchData {
        lateinit var gfeSearchData: GfeSearchData
        try {
            gfeSearchData = GfeSearchData(
                loci = stateContext.loci,
                version = stateContext.version,
                locus = stateContext.locus,
                checkBoxList = GfeViewMethods.checkList,
                textFieldList = GfeViewMethods.textList,
                regex = "".toRegex(),
                header = "",
                textFormat = whatOutputType(),
                writeToFile = printToFile(),
                dataFile = File(
                    GetDataFiles.retrieveDataFiles(
                        stateContext.loci,
                        stateContext.version,
                        stateContext.locus
                    )
                )
            )

            BuildRegexString.assembleRegexString(gfeSearchData)
            BuildHeaderString.assembleHeaderString(gfeSearchData)

        } catch (ex: Exception) {
            val noDataMsg = "GSG cannot create a search.\nPlease check to see if you have internet and data.\n" +
                    "Data files are located in Documents/GSG/GSGData/.\nIf the HLA folder is empty, you need to download data."

            gfeInformationTextArea.infoTextArea.appendText(noDataMsg)
            nameInformationTextArea.infoTextArea.appendText(noDataMsg)
        }

        return gfeSearchData
    }

    private fun whatOutputType(): String {
        val radioButtons = find(GfeRadioFileType::class)
        var fileType = "csv"
        if (radioButtons.printOptionTsv.isSelected) {
            fileType = "tsv"
        }

        return fileType
    }

    private fun printToFile(): Boolean {
        val printBox = find(GfeCheckboxPrint::class)
        return printBox.printCheckBox.isSelected
    }
}