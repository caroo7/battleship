package gui.panels.buttons;

import javax.swing.*;
import java.awt.event.ActionListener;

public enum Buttons {
    Generate("Generate ships", true), Start("Start", false), Rules("Rules", true),
    FourShoots("4 shoots", false), ThreeShoots("3 shoots", false), TwoShoots("2 shoots", false);

    private boolean isPermanentlyDisabled;
    private final JButton button;

    Buttons(String title, boolean isEnabledAtStart) {
        button = new JButton(title);
        button.setEnabled(isEnabledAtStart);
    }

    /**
     * Some buttons on below panel should be permanently disabled in some cases. This method change button status to disabled (freeze the button)
     * and by setting isPermanentlyDisabled to true doesn't allow to enable this button during the game
     */
    public void setPermanentlyDisabled() {
        setEnabled(false);
        isPermanentlyDisabled = true;
    }

    /**
     * Set button enabled (true) or disabled (freeze it - false)
     * @param enabled - true or false
     */
    public void setEnabled(boolean enabled) {
        if (!isPermanentlyDisabled) {
            button.setEnabled(enabled);
        }
    }

    void addListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    void addToPanel(JPanel panel) {
        panel.add(button);
    }
}
