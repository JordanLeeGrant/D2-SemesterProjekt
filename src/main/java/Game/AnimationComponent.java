package Game;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {

    private int speed = 0;

    private final AnimatedTexture texture;
    private final AnimationChannel playerAnimationIdle,playerAnimationWalk,playerAnimationJump;

    public AnimationComponent() {

        playerAnimationIdle = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 4, 50, 37, Duration.seconds(1), 0, 3);
        playerAnimationWalk = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 8, 13);
        playerAnimationJump = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 14, 21);

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
       /* entity.translateX(speed * tpf);

        if (speed != 0) {
            if (texture.getAnimationChannel() == playerAnimationIdle) {
                texture.loopAnimationChannel(playerAnimationWalk);
            }

            speed = (int) (speed * 0.9);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                texture.loopAnimationChannel(playerAnimationIdle);
            }
        }*/


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
