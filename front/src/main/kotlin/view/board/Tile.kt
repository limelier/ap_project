package view.board

import data.Piece
import javafx.geometry.Pos
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane

private enum class TileColor {
    WHITE,
    BLACK
}

/**
 * UI element for a board tile.
 */
class Tile(row: Int, column: Int) : StackPane() {
    /**
     * The [TileColor] of the current tile, determined by [row] and [column].
     */
    private val color = when ((row + column) % 2 == 0) {
        true -> TileColor.WHITE
        false -> TileColor.BLACK
    }

    /**
     * Holds the image for the piece on the tile, if one exists.
     */
    private val imageView = ImageView()

    /**
     * Sets the image contained in [imageView] to the one for the given [piece], or to no image if the value is `null`.
     */
    fun setPiece(piece: Piece?) {
        imageView.image = piece?.image
    }

    init {
        this.stylesheets.add("stylesheets/tile.css")
        this.styleClass.addAll(
            "tile",
            when (color) {
                TileColor.WHITE -> "tile--white"
                TileColor.BLACK -> "tile--black"
            }
        )

        imageView.fitWidth = 56.0
        imageView.fitHeight = 56.0
        imageView.isPreserveRatio = true

        alignment = Pos.CENTER

        prefWidth = 64.0
        prefHeight = 64.0

        children.add(imageView)
    }
}