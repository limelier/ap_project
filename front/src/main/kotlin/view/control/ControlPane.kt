package view.control

import javafx.geometry.Insets
import javafx.scene.control.Separator
import javafx.scene.layout.VBox

/**
 * Pane with controls for import/export and piece placement.
 */
class ControlPane : VBox() {
    /**
     * Pane with the currently selected color and rank.
     */
    val selectionPane = SelectionPane()

    /**
     * Pane with the 'Import board' and 'Export board' buttons.
     */
    val importExportPane = ImportExportPane()

    init {
        children.addAll(
            importExportPane,
            Separator(),
            selectionPane
        )
        padding = Insets(20.0)
        spacing = 10.0
    }
}