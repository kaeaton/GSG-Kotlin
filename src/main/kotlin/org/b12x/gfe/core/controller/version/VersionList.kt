package org.b12x.gfe.core.controller.version

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.b12x.gfe.core.model.datadownload.version.DownloadVersion
import org.b12x.gfe.core.model.datadownload.version.IncomingVersions
import org.b12x.gfe.plugins.gfesearch.view.GfeTextAreaInfo
import org.b12x.gfe.plugins.namesearch.view.NameSearchInformationTextArea
import org.b12x.gfe.utilities.InternetAccess
import tornadofx.find

class VersionList(loci: String) {
    private val localVersions = LocalVersions(loci)
    var allVersionNames = listOf<String>()
    val availableVersions = ArrayList<String>()
    private var onlineVersionList = mutableListOf<String>()

    init { generateOnlineVersionNames() }

    private fun generateOnlineVersionNames() {

        runBlocking {
            if (InternetAccess().internetStatus) {
                onlineVersionList = getOnlineVersionList()
            } else {
                // allows the version list to populate without internet

                val localVersionNames = mutableListOf<String>()

                // arraylist of Version objects
                localVersions.versionsList.forEach {
                    localVersionNames.add(it.name)
                }

                allVersionNames = localVersionNames.sortedDescending()
            }
        }
    }

    suspend private fun getOnlineVersionList(): ArrayList<String> {
        var incomingVersions: IncomingVersions
        val versionList = arrayListOf<String>()

        coroutineScope {
            launch() {
                try {
                    incomingVersions = DownloadVersion.getVersions("HLA")
                    println(incomingVersions)

                    incomingVersions.imgt_versions.forEach {
                        println(it)

                        var finalVersionName = StringBuilder()
                        finalVersionName.append(it.get(0))
                        finalVersionName.append(".")
                        finalVersionName.append(it.get(1))
                        finalVersionName.append(it.get(2))
                        finalVersionName.append(".")
                        finalVersionName.append(it.get(3))

                        println(finalVersionName.toString())

                        versionList.add(finalVersionName.toString())
                    }
                    allVersionNames = combineVersionLists(versionList).sortedDescending()
                    println(allVersionNames)
                } catch(e: Exception) { }

//        val directoryManagement = DirectoryManagement()
//        val versionFileLocation = FileManagement.createFile(
//            directoryManagement.setLociLocation(loci),
//            "onlineVersions",
//            "txt")
//        val versionsFile = localVersions.returnOnlineVersionFile()
//        versionsFile.forEachLine {
//            versionList.add(it.name)
            }
        }
        return versionList
    }

    private fun combineVersionLists(newVersions: ArrayList<String>): MutableList<String> {
        val localVersionNames = mutableListOf<String>()

        // arraylist of Version objects
        localVersions.versionsList.forEach {
            localVersionNames.add(it.name)
        }

        newVersions.forEach { onlineVersion ->
            if (!localVersionNames.contains(onlineVersion)) {
                localVersionNames.add(onlineVersion)

                val gfeInformationTextArea = find(GfeTextAreaInfo::class)
                val nameInformationTextArea = find(NameSearchInformationTextArea::class)

                gfeInformationTextArea.infoTextArea.appendText("New version available: $onlineVersion\n")
                nameInformationTextArea.infoTextArea.appendText("New version available: $onlineVersion\n")
            }
        }
        println(localVersionNames)
        return localVersionNames
    }

    private fun createVersionList() {

    }


}