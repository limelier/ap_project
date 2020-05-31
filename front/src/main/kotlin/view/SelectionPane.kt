package view

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

    private fun changePieceButtonColors(color: Color) {
        pieceButtons.forEach { it.changeColor(color) }
    }

    init {
        val colorRoot = initColorRoot()
        val pieceRoot = initPieceRoot()
        children.addAll(colorRoot, Separator(Orientation.HORIZONTAL), pieceRoot)
        alignment = Pos.CENTER
        spacing = 5.0
        padding = Insets(20.0)
    }

    private fun initPieceRoot(): VBox {
        val pieceRoot = VBox()
        val btnDelete = SelectButton("Erase")
        btnDelete.isSelected = true
        btnDelete.prefWidthProperty().bind(pieceButtons[0].widthProperty())
        btnDelete.prefHeight = 64.0
        val rankGroup = ToggleGroup()
        rankGroup.toggles.add(btnDelete)
        rankGroup.toggles.addAll(pieceButtons)
        rankGroup.selectedToggleProperty().addListener { _, _, value ->
            val rank = when (value) {
                btnDelete -> null
                is PieceButton -> value.getRank()
                else -> throw Error("Invalid rank toggle.")
            }
            rankProperty.set(rank)
        }
        pieceRoot.children.add(btnDelete)
        pieceRoot.children.addAll(pieceButtons)
        pieceRoot.alignment = Pos.CENTER
        return pieceRoot
    }

    private fun initColorRoot(): HBox {
        val colorRoot = HBox()
        val btnWhite = SelectButton("White")
        btnWhite.isSelected = true
        val btnBlack = SelectButton("Black")
        val colorGroup = ToggleGroup()
        colorGroup.toggles.addAll(btnWhite, btnBlack)
        colorGroup.selectedToggleProperty().addListener { _, _, value ->
            val color = when (value) {
                btnWhite -> Color.WHITE
                btnBlack -> Color.BLACK
                else -> throw Error("Invalid color toggle.")
            }
            colorProperty.set(color)
            changePieceButtonColors(color)
        }
        colorRoot.children.addAll(btnWhite, btnBlack)
        colorRoot.alignment = Pos.CENTER
        return colorRoot
    }
}