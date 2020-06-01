package model

import data.Piece
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty

class BoardModel {
    val name = SimpleStringProperty("")
    val description = SimpleStringProperty("")
    val pieces = Array(8) { Array(8) { SimpleObjectProperty<Piece?>() } }
}