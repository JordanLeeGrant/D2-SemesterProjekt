package Game;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {

    public void left() {
        entity.getComponent(AnimationComponent.class).moveLeft();
    }

    public void right() {
        entity.getComponent(AnimationComponent.class).moveRight();

    }

    public void jump() {

    }

    public void attack() {

    }
}
