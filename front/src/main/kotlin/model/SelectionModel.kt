package model

import data.Color
import data.Rank
import javafx.beans.property.SimpleObjectProperty

class SelectionModel {
    val colorProperty = SimpleObjectProperty(Color.WHITE)
    val rankProperty = SimpleObjectProperty<Rank>(null)
}