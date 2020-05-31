package controller

import model.SelectionModel
import view.SelectionPane

class SelectionController(model: SelectionModel, view: SelectionPane) {
    init {
        model.colorProperty.bind(view.colorProperty)
        model.rankProperty.bind(view.rankProperty)
    }
}