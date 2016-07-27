package gui.panels.buttons;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class ListenersFactory {
    Map<Buttons,ActionListener> listeners = new HashMap<>();

   ListenersFactory(){
        initListenersMap();
    }

   ActionListener getListenerForButton(Buttons button){
        return listeners.getOrDefault(button,e->{});
    }


    private void initListenersMap(){
        listeners.put(Buttons.Generate,generateShipsAction());
        listeners.put(Buttons.Start,startAction());
        listeners.put(Buttons.Rules,rulesAction());
        listeners.put(Buttons.FourShoots,fourShootsAction());
        listeners.put(Buttons.ThreeShoots,threeShootsAction());
        listeners.put(Buttons.TwoShoots,twoShootsAction());
    }

    /* TODO
    *   implement all below action methods
    * */

    private ActionListener generateShipsAction(){
        return e -> {
            System.out.println("Generate");
        };
    }

    private ActionListener startAction(){
        return e -> {
            System.out.println("Start");
        };
    }

    private ActionListener rulesAction(){
        return e -> {
            System.out.println("Rules");
        } ;
    }

    private ActionListener fourShootsAction(){
        return e -> {
            System.out.println("Four shoots");
        };
    }

    private ActionListener threeShootsAction(){
        return e -> {
            System.out.println("Three shoots");
        };
    }

    private ActionListener twoShootsAction(){
        return e ->{
            System.out.println("Two shoots");
        };
    }
}
