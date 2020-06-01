import controller.BoardController
import controller.ImportExportController
import controller.SelectionController
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.stage.Stage
import model.BoardModel
import model.SelectionModel
import view.board.BoardView
import view.control.ControlPane
import java.util.*

const val size = 8

class App : Application() {
    override fun start(stage: Stage) {
        val boardView = BoardView()
        val controlView = ControlPane()

        val boardModel = BoardModel()
        val selectionModel = SelectionModel()

        BoardController(boardModel, boardView, selectionModel)
        SelectionController(selectionModel, controlView.selectionPane)
        ImportExportController(boardModel, controlView.importExportPane)

        val root = HBox(boardView, controlView)

        val loc = ResourceBundle.getBundle("locale.Main")
        stage.apply {
            scene = Scene(root, 760.0, 700.0)
            title = loc.getString("appTitle")
            show()
        }
    }
}

fun main() {
    Application.launch(App::class.java)
}