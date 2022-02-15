package org.b12x.gfe

import org.b12x.gfe.utilities.InternetAccess
import tornadofx.launch
import java.io.File


fun main() {
    InternetAccess.internetAccess = InternetAccess.isInternetAvailable()
    println("Internet access? " + InternetAccess.internetAccess)

    launch<GSG>()
}


