import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage
import view.board.BoardView
import view.control.ControlPane

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