package controller

import model.SelectionModel
import view.control.SelectionPane

/**
 * Controller binding the current selection [model] to the corresponding [view].
 */
class SelectionController(model: SelectionModel, view: SelectionPane) {
    init {
        model.colorProperty.bind(view.colorProperty)
        model.rankProperty.bind(view.rankProperty)
    }
}