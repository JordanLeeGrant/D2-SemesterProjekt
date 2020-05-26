package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Application extends GameApplication {
    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1050);
        settings.setHeight(700);
        settings.setMenuEnabled(false);
        settings.setTitle("JumpToBeat-Demo");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new JumpToBeatEntityFactory());
        player = spawn("player", 525, 350);
        setLevelFromMap("TestLevel.tmx");
        FXGL.entityBuilder()
                .type(EntityType.COIN)
                .at(500, 200)
                .viewWithBBox(new Circle(15, Color.YELLOW))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
                FXGL.play("smw_1-up.wav");
            }

        });
    }

    @Override
    protected void initUI() {
        Text distanceCount = new Text();
        distanceCount.setTranslateX(50);
        distanceCount.setTranslateY(100);
        distanceCount.textProperty().bind(FXGL.getGameState().intProperty("pixels Moved").asString());
        FXGL.getGameScene().addUINode(distanceCount);
       /* var brickTexture = FXGL.getAssetLoader().loadTexture("PlayerDemoIcon.png");
        brickTexture.setTranslateX(50);
        brickTexture.setTranslateY(450);

        FXGL.getGameScene().addUINode(brickTexture);*/
    }

    @Override
    protected void initInput() {
        Input input = FXGL.getInput();

        input.addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right();
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left();
            }
        }, KeyCode.A);


        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.translateY(5);
                FXGL.getGameState().increment("pixels Moved", +5);
            }
        }, KeyCode.S);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.translateY(-5);
                FXGL.getGameState().increment("pixels Moved", +5);
            }
        }, KeyCode.W);
        input.addAction(new UserAction("Play Sound") {
            @Override
            protected void onActionBegin() {
                FXGL.play("smw_1-up.wav");
            }
        }, KeyCode.F);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixels Moved", 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
