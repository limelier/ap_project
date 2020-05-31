import controller.BoardController
import data.Color
import data.Piece
import data.Rank
import javafx.application.Application
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.TilePane
import javafx.stage.Stage
import model.BoardModel
import view.BoardView
import view.ControlPane
import view.SelectionPane
import view.Tile

const val size = 8

class App : Application() {
    override fun start(primaryStage: Stage?) {
        val root = HBox()

        val boardView = BoardView()
        val controlView = ControlPane()

        root.children.addAll(boardView, controlView)

        val scene = Scene(root)
        primaryStage!!.scene = scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(App::class.java)
}