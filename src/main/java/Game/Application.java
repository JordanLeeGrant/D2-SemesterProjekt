package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.level.Level;
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
        var level = setLevelFromMap("TestLevel.tmx");
        player = spawn("player", 300, 300);
        /*
              FXGL.entityBuilder()
                .at(525,350)
                .view("Tests/PlayerDemoIcon.png")
                .with(new PlayerComponent())
                .buildAndAttach();*/
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
                FXGL.play("smw_1-up.wav"); }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.PLATFORM)
        {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin)
            {
                player.getComponent(PlayerComponent.class).resetJumps();
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

    }

    @Override
    protected void initInput() {
        Input input = FXGL.getInput();

       input.addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).moveplayerRight();
            }
        }, KeyCode.D);

       input.addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).moveplayerLeft();
            }
        }, KeyCode.A);
       input.addAction(new UserAction("Jump") {
            @Override
            protected void onActionBegin() {
                player.getComponent(PlayerComponent.class).playerJump();
            }
        }, KeyCode.SPACE);
       input.addAction(new UserAction("Play Sound") {
            @Override
            protected void onActionBegin() { FXGL.play("smw_1-up.wav");
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
