package Game;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    public void left() {
        //entity.getComponent(AnimationComponent.class).moveLeft();
        entity.getComponent(PhysicsComponent.class).setVelocityX(-100);
       // entity.translateX(-5);
    }

    public void right() {
       // entity.getComponent(AnimationComponent.class).moveRight();
        //entity.translateX(5);
        entity.getComponent(PhysicsComponent.class).setVelocityX(100);

    }

    public void jump() {

        entity.getComponent(PhysicsComponent.class).setVelocityY(-200);
    }

    public void attack() {

    }
}
