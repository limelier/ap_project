import javafx.geometry.Pos
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane

enum class TileColor {
    WHITE,
    BLACK
}

class Tile(row: Int, column: Int) : StackPane() {
    private val color = when((row + column) % 2 == 0) {
        true -> TileColor.WHITE
        false -> TileColor.BLACK
    }

    private val imageView = ImageView()

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