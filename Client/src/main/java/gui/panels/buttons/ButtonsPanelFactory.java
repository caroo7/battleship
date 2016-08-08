package gui.panels.buttons;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.EnumSet;

public class ButtonsPanelFactory {

    @Autowired
    private ListenersFactory listenersFactory;

    /**
     * create buttons panel. Buttons are enum so we can easily add them to panel and add appropriate listeners to them
     * @return created buttons panel
     */
    public JPanel getButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        EnumSet.allOf(Buttons.class).stream().forEach(button->{
            button.addListener(listenersFactory.getListenerForButton(button));
            button.addToPanel(buttonsPanel);
        });
        return buttonsPanel;
    }
}
