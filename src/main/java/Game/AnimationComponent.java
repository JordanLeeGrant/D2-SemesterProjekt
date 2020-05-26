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

    private AnimatedTexture texture;
    private AnimationChannel animationIdle, animationWalk;

    public AnimationComponent(){

        animationIdle = new AnimationChannel(FXGL.image("Girl_idle.png"),4,48,48, Duration.seconds(1),0,3);
        animationWalk = new AnimationChannel(FXGL.image("Girl_walk.png"),6,48,48, Duration.seconds(1),0,5);

        texture = new AnimatedTexture(animationIdle);
    }
    @Override
    public void onAdded(){
        entity.getTransformComponent().setScaleOrigin(new Point2D(16,21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf){
        entity.translateX(speed*tpf);

        if (speed !=0){
            if(texture.getAnimationChannel() == animationIdle){
                texture.loopAnimationChannel(animationWalk);
            }

            speed = (int) (speed*0.9);

            if (FXGLMath.abs(speed)<1){
                speed = 0;
                texture.loopAnimationChannel(animationIdle);
            }
        }

    }
    public void moveRight(){
        speed = 150;

        getEntity().setScaleX(1);
    }

    public void moveLeft(){
        speed = -150;

        getEntity().setScaleX(-1);
    }

}
