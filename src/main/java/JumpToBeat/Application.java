package JumpToBeat;

import JumpToBeat.Components.DoorComponent;
import JumpToBeat.Components.PlayerComponent;
import JumpToBeat.DataHandler.DataHandler;
import JumpToBeat.Entities.EntityType;
import JumpToBeat.Factories.JumpToBeatEntityFactory;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;

import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Application extends GameApplication {
    private Entity playerSuper, edoor;
    private long startTime, endTime;
    private final int coincount = 20;
    private DataHandler handle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1050);
        settings.setHeight(700);
        settings.setMenuEnabled(true);
        settings.setTitle("JumpToBeat-Demo");
        settings.setVersion("0.1");

    }

    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new JumpToBeatEntityFactory());
        var level = setLevelFromMap("Map/unbenannt.tmx");
        // var level = setLevelFromMap("TestLevel.tmx");

        playerSuper = spawn("player");
        edoor = spawn("door");

        Viewport playerCamera = getGameScene().getViewport();
        playerCamera.bindToEntity(playerSuper, playerCamera.getWidth() / 2, playerCamera.getHeight() / 2);
        playerCamera.setBounds(0, 0, 1500, (int) playerCamera.getHeight());

        startTime = System.currentTimeMillis();

        /* FXGL.entityBuilder()
          .at(525,350)
          .view("Tests/PlayerDemoIcon.png")
          .with(new PlayerComponent())
          .buildAndAttach();
          */
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
                FXGL.play("smw_coin.wav");
                FXGL.getGameState().increment("coins", -1);
                if (FXGL.getGameState().intProperty("coins").intValue() == 0) {
                    edoor.getComponent(DoorComponent.class).Activate();
                    getDisplay().showMessageBox("Ok good job now run to the end the left side to finish the level ", () -> {
                    });
                }
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMYJUMPER) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                FXGL.play("dmg.wav");
                if (player.getComponent(PlayerComponent.class).Damage()) {
                    spawn("player");

                } else {
                    player.getComponent(PhysicsComponent.class).setVelocityX(player.getComponent(PhysicsComponent.class).getVelocityX() * -1);
                }
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMYPATROL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                FXGL.play("dmg.wav");
                if (player.getComponent(PlayerComponent.class).Damage()) {
                    spawn("player");
                } else {
                    player.getComponent(PhysicsComponent.class).setVelocityX(player.getComponent(PhysicsComponent.class).getVelocityX() * -1);
                }
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMYSEEKER) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                FXGL.play("dmg.wav");
                if (player.getComponent(PlayerComponent.class).Damage()) {
                    spawn("player");

                } else {
                    player.getComponent(PhysicsComponent.class).setVelocityX(player.getComponent(PhysicsComponent.class).getVelocityX() * -1);
                }
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                if (FXGL.getGameState().intProperty("coins").intValue() == 0) {
                    String score = String.valueOf(makeScore());
                    getDisplay().showMessageBox("Good Job You Won with a Score of " + score, () -> {
                        handle = new DataHandler();
                        handle.addnewData("HiSc", score);
                        handle.SaveData();
                        handle.updateHighscore();
                        int[] hiscore = handle.getHighscoreTable();
                        getDisplay().showMessageBox("Highscores:\n" + hiscore[0] + "\n" + hiscore[1] + "\n" + hiscore[2] + "\n" + hiscore[3] + "\n" + hiscore[4] + "\n" + hiscore[5] + "\n" + hiscore[6] + "\n" + hiscore[7] + "\n" + hiscore[8] + "\n" + hiscore[9]);
                        player.getComponent(PlayerComponent.class).Reset();
                        FXGL.getGameState().intProperty("coins").setValue(coincount);
                        getGameWorld().clear();
                        initGame();
                    });
                }
            }
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("coins", coincount);
        vars.put("Score", 0);
        vars.put("Time", 0);
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100
        textPixels.textProperty().bind(FXGL.getGameState().intProperty("coins").asString());

        FXGL.getGameScene().addUINode(textPixels);
    }

    @Override
    protected void initInput() {
        Input input = FXGL.getInput();

        input.addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                playerSuper.getComponent(PlayerComponent.class).movePlayerRight();
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                playerSuper.getComponent(PlayerComponent.class).movePlayerLeft();
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Jump") {
            @Override
            protected void onActionBegin() {
                playerSuper.getComponent(PlayerComponent.class).playerJump();
            }
        }, KeyCode.SPACE);
        input.addAction(new UserAction("Play Sound") {
            @Override
            protected void onActionBegin() {
                FXGL.play("smw_1-up.wav");
            }
        }, KeyCode.F);
    }

    protected double timer() {
        long now = System.currentTimeMillis();
        return (now - startTime) / 1000.0;
    }

    private int makeScore() {
        double score = playerSuper.getComponent(PlayerComponent.class).get_playerHitpoints() * 100 + coincount * 10 - timer() * 2;
        return (int) score;
    }

}
