package Game;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    public void left() {
        //entity.getComponent(AnimationComponent.class).moveLeft();
        entity.translateX(-5);
    }

    public void right() {
       // entity.getComponent(AnimationComponent.class).moveRight();
        entity.translateX(5);

    }

    public void jump() {

    }

    public void attack() {

    }
}
