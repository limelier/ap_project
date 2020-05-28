import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import java.net.URL

const val size = 8

class App : Application() {
    override fun start(primaryStage: Stage?) {
        val loader = FXMLLoader()
        loader.location = javaClass.getResource("/App.fxml")
        val root = loader.load<GridPane>()

        val scene = Scene(root, 800.0, 600.0)
        primaryStage!!.scene = scene
        primaryStage.show()
    }

    fun print() {
        println("hello")
    }
}

fun main() {
    Application.launch(App::class.java)
}