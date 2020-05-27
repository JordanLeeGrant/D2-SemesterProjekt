package Game;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    private int playerJumps,playerHitpoints;


    public PlayerComponent()
    {
        playerHitpoints = 3;
        playerJumps = 2;
    }
    public void left() {
        entity.getComponent(PhysicsComponent.class).setVelocityX(-200);
    }

    public void right() {
        entity.getComponent(PhysicsComponent.class).setVelocityX(200);

    }

    public void jump() {
        if (playerJumps>0) {
            entity.getComponent(PhysicsComponent.class).setVelocityY(-200);
            playerJumps--;
        }
    }

    public void resetJumps() {
        playerJumps = 2;
    }
}
