import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.layout.TilePane
import javafx.stage.Stage

const val size = 8

class App : Application() {
    override fun start(primaryStage: Stage?) {
        val root = TilePane()
        root.alignment = Pos.CENTER

        var tile = Tile(TileColor.WHITE)

        for (i in 0..8) {
            for (j in 0..8) {
                tile = Tile(
                    when((i + j) % 2 == 0) {
                        true -> TileColor.BLACK
                        false -> TileColor.WHITE
                    },
                    null
                )
                root.children.add(tile)
            }
        }

        tile.displayPiece(Piece(Color.WHITE, Rank.QUEEN))

        val scene = Scene(root, 800.0, 600.0)
        primaryStage!!.scene = scene
        primaryStage.show()

    }
}

fun main() {
    Application.launch(App::class.java)
}