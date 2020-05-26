package Game;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {

    private int speed = 0;

    private final AnimatedTexture texture;
    private final AnimationChannel playerAnimationIdle;
    private final AnimationChannel playerAnimationWalk;
    private final AnimationChannel playerAnimationJump;

    public AnimationComponent() {

        playerAnimationIdle = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 4, 50, 37, Duration.seconds(1), 0, 3);
        playerAnimationWalk = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 8, 13);
        playerAnimationJump = new AnimationChannel(FXGL.image("adventurer-Sheet.png"), 7, 50, 37, Duration.seconds(1), 14, 21);

        texture = new AnimatedTexture(playerAnimationIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(24, 24));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(speed * tpf);

        if (speed != 0) {
            if (texture.getAnimationChannel() == playerAnimationIdle) {
                texture.loopAnimationChannel(playerAnimationWalk);
            }

            speed = (int) (speed * 0.9);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                texture.loopAnimationChannel(playerAnimationIdle);
            }
        }

    }

    public void moveRight() {
        speed = 150;

        getEntity().setScaleX(1);
    }

    public void moveLeft() {
        speed = -150;

        getEntity().setScaleX(-1);
    }

}
