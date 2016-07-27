package gui.services;

import java.util.ArrayList;
import java.util.List;

public abstract class Publisher {
    private List<Subscriber> subscribersList = new ArrayList<>();

    public void subscribe(Subscriber subscriber){
        subscribersList.add(subscriber);
    }

   public void unsubscribe(Subscriber subscriber){
        subscribersList.remove(subscriber);
    }


    public void notifyAllSubsbscribers(DataObject dataObject){
        subscribersList.stream().forEach(subscriber -> subscriber.update(dataObject));
    }

}
