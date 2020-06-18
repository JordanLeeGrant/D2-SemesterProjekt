package JumpToBeat;


import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {

    private int playerJumps,playerHitpoints;
    private PhysicsComponent playerPhysics;

    public PlayerComponent()
    {

        playerHitpoints = 3;
        playerJumps = 2;
    }
    public void movePlayerLeft() {
        entity.getComponent(PhysicsComponent.class).setVelocityX(-200);
        entity.getComponent(PlayerAnimationComponent.class).moveplayerLeft();
    }

    public void movePlayerRight() {
        entity.getComponent(PhysicsComponent.class).setVelocityX(200);
        entity.getComponent(PlayerAnimationComponent.class).moveplayerRight();

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
