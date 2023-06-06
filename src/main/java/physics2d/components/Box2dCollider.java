package physics2d.components;

import components.Component;
import org.joml.Vector2f;

public class Box2dCollider extends Component {
    public Vector2f getHalfSize() {
        return halfSize;
    }

    public void setHalfSize(Vector2f halfSize) {
        this.halfSize = halfSize;
    }

    private Vector2f halfSize = new Vector2f(1);

}
