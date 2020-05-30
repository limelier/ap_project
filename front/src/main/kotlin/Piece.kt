import javafx.scene.image.Image
import java.io.FileInputStream

enum class Color {
    WHITE,
    BLACK
}

enum class Rank {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING
}

class Piece(
    val color: Color,
    val rank: Rank
) {
    val image: Image
        get() = getImage(this)

    companion object {
        private val pieceImages: Map<Piece, Image>

        private fun loadImage(piece: Piece): Image {
            val color = piece.color.name.toLowerCase()
            val rank = piece.rank.name.toLowerCase()

            return Image(FileInputStream("src/main/resources/images/${color}_${rank}.png"))
        }

        @Suppress("MapGetWithNotNullAssertionOperator")
        // the map is populated on init, NPE cannot occur
        private fun getImage(piece: Piece): Image {
            return pieceImages[piece]!!
        }

        init {
            val ranks = Rank.values()
            val colors = Color.values()
            val pieces = ranks.flatMap { rank -> colors.map { color -> Piece(color, rank) } }
            pieceImages = pieces.associateWith { loadImage(it) }
        }
    }
}

