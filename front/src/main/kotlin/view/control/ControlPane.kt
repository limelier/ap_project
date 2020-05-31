package view.control

import javafx.geometry.Insets
import javafx.scene.control.Separator
import javafx.scene.layout.VBox

class ControlPane : VBox() {
    val selectionPane = SelectionPane()

    init {
        children.addAll(
            ImportExportPane(),
            Separator(),
            selectionPane
        )
        padding = Insets(20.0)
        spacing = 10.0
    }
}