package psycho;

import util.ColoredLogger;

public class LevelScene extends Scene {
    public LevelScene() {
        ColoredLogger.info("LevelScene constructed.");
        Window.get().r = 1;
        Window.get().g = 1;
        Window.get().b = 1;
    }

    @Override
    public void update(float dt) {

    }
}
