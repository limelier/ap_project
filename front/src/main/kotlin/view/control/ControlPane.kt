package view.control

import javafx.geometry.Insets
import javafx.scene.control.Separator
import javafx.scene.layout.VBox

class ControlPane : VBox() {
    init {
        children.addAll(
            ImportExportPane(),
            Separator(),
            SelectionPane()
        )
        padding = Insets(20.0)
        spacing = 10.0
    }
}