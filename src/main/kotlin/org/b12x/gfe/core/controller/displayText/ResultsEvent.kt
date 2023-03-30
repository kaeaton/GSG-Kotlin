package org.b12x.gfe.core.controller.displayText

import org.b12x.gfe.core.controller.SearchData
import tornadofx.*
import tornadofx.EventBus.RunOn.*


class ResultsEvent(val results: List<Result>) : FXEvent() {
}