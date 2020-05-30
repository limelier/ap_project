package view

import javafx.event.ActionEvent
import javafx.scene.control.ToggleButton

/**
 * A version of ToggleButton which doesn't allow you to deselect
 * if already selected (much like a RadioButton).
 */
open class SelectButton : ToggleButton() {
    override fun fire() {
        if (!isDisabled && !isSelected) {
            isSelected = true
            fireEvent(ActionEvent())
        }
    }
}