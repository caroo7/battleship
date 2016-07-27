package gui.panels.buttons;

import javax.swing.*;
import java.awt.event.ActionListener;

public enum Buttons {
    Generate("Generate ships"),Start("Start"),Rules("Rules"),
    FourShoots("4 shoots"),ThreeShoots("3 shoots"),TwoShoots("2 shoots");

    private String title;
    private JButton button ;

    Buttons(String title){
      button = new JButton(title);
    }

    public void setEnabled(boolean enabled){
        button.setEnabled(enabled);
    }

    void addListener(ActionListener actionListener){
        button.addActionListener(actionListener);
    }

    void addToPanel(JPanel panel){
        panel.add(button);
    }
}
