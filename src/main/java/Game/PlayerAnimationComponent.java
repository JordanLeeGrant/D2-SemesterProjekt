package Game;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class PlayerAnimationComponent extends Component {

    private int speed = 0;

    private final AnimatedTexture texture;
    private final AnimationChannel playerAnimationIdle,playerAnimationWalk,playerAnimationJump,playerAnimationAttack1,playerAnimationAttack2,playerAnimationAttack3;

    public PlayerAnimationComponent() {

        playerAnimationIdle = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 4, 50, 37, Duration.seconds(1), 0, 3);
        playerAnimationWalk = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 8, 13);
        playerAnimationJump = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 14, 21);
        playerAnimationAttack1 = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 6, 50, 37, Duration.seconds(1), 48, 21);
        playerAnimationAttack2 = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 14, 21);
        playerAnimationAttack3 = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 14, 21);
        texture = new AnimatedTexture(playerAnimationIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(25, 18));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

        if (!isMoving())
        {
            texture.playAnimationChannel(playerAnimationIdle);
        }
        else
        {
            texture.playAnimationChannel(playerAnimationWalk);
        }
        entity.translateX(speed * tpf);

        if (speed != 0) {
            if (texture.getAnimationChannel() == playerAnimationIdle) {
                texture.playAnimationChannel(playerAnimationWalk);
            }

            speed = (int) (speed * 0.9);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                texture.playAnimationChannel(playerAnimationIdle);
            }
        }


    }
    private boolean isMoving()
    {
        return Math.abs(entity.getComponent(PhysicsComponent.class).getVelocityX()) > 0;
    }
    public void moveplayerRight() {
        getEntity().setScaleX(1);
    }

    public void moveplayerLeft() {
        getEntity().setScaleX(-1);
    }

}
