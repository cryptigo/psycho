package scenes;

import psycho.Window;
import scenes.Scene;
import util.Logger;

public class LevelScene extends Scene {
    public LevelScene() {
        Logger.logInfo("Created LevelScene object.");
        Window.get().r = 1;
        Window.get().g = 1;
        Window.get().b = 1;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {

    }
}
