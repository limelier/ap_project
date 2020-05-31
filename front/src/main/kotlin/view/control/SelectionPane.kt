package view.control

import data.Color
import data.Piece
import data.Rank
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Separator
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class SelectionPane : VBox() {
    val colorProperty = SimpleObjectProperty(Color.WHITE)
    val rankProperty = SimpleObjectProperty<Rank>(null)

    private val pieceButtons = Rank.values().map { PieceButton(Piece(Color.WHITE, it)) }

    private fun changePieceButtonColors(color: Color) = pieceButtons.forEach { it.changeColor(color) }

    init {
        children.addAll(initColorRoot(), Separator(Orientation.HORIZONTAL), initPieceRoot())
        alignment = Pos.CENTER
        spacing = 5.0
        padding = Insets(0.0, 20.0, 0.0, 20.0)
    }

    private fun initPieceRoot(): VBox {
        val btnDelete = SelectButton("Delete").apply {
            isSelected = true
            prefWidthProperty().bind(pieceButtons[0].widthProperty())
        }
        ToggleGroup().apply {
            toggles.add(btnDelete)
            toggles.addAll(pieceButtons)
            selectedToggleProperty().addListener { _, _, value ->
                val rank = when (value) {
                    btnDelete -> null
                    is PieceButton -> value.getRank()
                    else -> throw Error("Invalid rank toggle.")
                }
                rankProperty.set(rank)
            }
        }
        return VBox()
            .apply {
            children.add(btnDelete)
            children.addAll(pieceButtons)
            alignment = Pos.CENTER
        }
    }

    private fun initColorRoot(): HBox {
        val btnWhite = SelectButton("White")
        btnWhite.isSelected = true
        val btnBlack = SelectButton("Black")
        ToggleGroup().apply {
            toggles.addAll(btnWhite, btnBlack)
            selectedToggleProperty().addListener { _, _, value ->
                val color = when (value) {
                    btnWhite -> Color.WHITE
                    btnBlack -> Color.BLACK
                    else -> throw Error("Invalid color toggle.")
                }
                colorProperty.set(color)
                changePieceButtonColors(color)
            }
        }

        return HBox().apply {
            children.addAll(btnWhite, btnBlack)
            alignment = Pos.CENTER
        }
    }
}