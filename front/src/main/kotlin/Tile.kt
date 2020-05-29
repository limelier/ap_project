import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import java.io.FileInputStream

enum class TileColor {
    WHITE,
    BLACK
}

class Tile(color: TileColor, piece: Piece? = null) : StackPane() {
    companion object {
        val pieceImages: Map<Piece, Image>

        private fun imageForPiece(piece: Piece): Image {
            val color = piece.color.name.toLowerCase()
            val rank = piece.rank.name.toLowerCase()

            return Image(FileInputStream("src/main/resources/images/${color}_${rank}.png"))
        }

        init {
            val ranks = Rank.values()
            val colors = Color.values()
            val pieces = ranks.flatMap { rank -> colors.map { color -> Piece(color, rank) } }
            pieceImages = pieces.associateWith { imageForPiece(it) }
        }
    }

    private val imageView = ImageView()

    fun displayPiece(piece: Piece?) {
        imageView.image = if (piece == null) {
            null
        } else {
            pieceImages[piece]
        }
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

        displayPiece(piece)
        imageView.fitWidth = 56.0
        imageView.fitHeight = 56.0
        imageView.isPreserveRatio = true

        alignment = Pos.CENTER

        prefWidth = 64.0
        prefHeight = 64.0

        children.add(imageView)
    }
}