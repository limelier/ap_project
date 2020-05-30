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

        for (i in 0..7) {
            for (j in 0..7) {
                root.children.add(Tile(i, j))
            }
        }

        val scene = Scene(root, 800.0, 600.0)
        primaryStage!!.scene = scene
        primaryStage.show()

    }
}

fun main() {
    Application.launch(App::class.java)
}