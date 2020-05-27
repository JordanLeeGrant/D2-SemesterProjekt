package Game;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    private int playerJumps,playerHitpoints;
    private AnimatedTexture playerTexture;
    private PhysicsComponent playerPhysics;

    public PlayerComponent()
    {
        playerHitpoints = 3;
        playerJumps = 2;
    }
    public void moveplayerLeft() {
        entity.getComponent(AnimationComponent.class).moveplayerLeft();
        entity.getComponent(PhysicsComponent.class).setVelocityX(-200);
    }

    public void moveplayerRight() {
        entity.getComponent(AnimationComponent.class).moveplayerRight();
        entity.getComponent(PhysicsComponent.class).setVelocityX(200);

    }

    public void playerJump() {
        if (playerJumps>0) {
            entity.getComponent(PhysicsComponent.class).setVelocityY(-200);
            playerJumps--;
        }
    }

    public void resetJumps() {
        playerJumps = 2;
    }
}
