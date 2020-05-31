import controller.BoardController
import controller.SelectionController
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage
import model.BoardModel
import model.SelectionModel
import view.board.BoardView
import view.control.ControlPane

const val size = 8

class App : Application() {
    override fun start(stage: Stage) {
        val boardView = BoardView()
        val controlView = ControlPane()

        val boardModel = BoardModel()
        val selectionModel = SelectionModel()

        BoardController(boardModel, boardView, selectionModel)
        SelectionController(selectionModel, controlView.selectionPane)

        val root = HBox(boardView, controlView)

        stage.apply {
            scene = Scene(root, 760.0, 700.0)
            title = "Chess scenario tool"
            show()
        }
    }
}

fun main() {
    Application.launch(App::class.java)
}