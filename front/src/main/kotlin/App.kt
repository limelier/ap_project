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

        val tile = Tile(TileColor.WHITE, Piece(Color.BLACK, Rank.QUEEN))

        root.children.add(tile)

        val scene = Scene(root, 800.0, 600.0)
        primaryStage!!.scene = scene
        primaryStage.show()

        tile.displayPiece(Piece(Color.WHITE, Rank.PAWN))
    }
}

fun main() {
    Application.launch(App::class.java)
}