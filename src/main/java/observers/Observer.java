package observers;

import observers.events.Event;
import psycho.GameObject;

public interface Observer {
    void onNotify(GameObject object, Event event);
}
