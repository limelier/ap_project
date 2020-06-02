package view.control

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import java.util.*

/**
 * Resource bundle containing localized strings.
 */
private val loc = ResourceBundle.getBundle("locale.Main")

/**
 * Panel holding buttons that allow the user to import or export a board through the API.
 */
class ImportExportPane : VBox() {

    val importButton = Button(loc.getString("importButton"))
    val exportButton = Button(loc.getString("exportButton"))

    init {
        importButton.prefWidthProperty().bind(widthProperty())
        exportButton.prefWidthProperty().bind(widthProperty())

        children.addAll(importButton, exportButton)
        spacing = 5.0
        alignment = Pos.CENTER
    }
}