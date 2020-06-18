package JumpToBeat;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class JumpToBeatEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent playerPhysics = new PhysicsComponent();
        playerPhysics.setBodyType(BodyType.DYNAMIC);

        return entityBuilder()
                .type(EntityType.PLAYER)
                .at(data.getX(),data.getY())
                .bbox(new HitBox(BoundingShape.box(50,37)))
                .with(new PlayerComponent())
                .with(new PlayerAnimationComponent())
                .with(new CollidableComponent(true))
                .with(playerPhysics)
                .build();
    }

    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return entityBuilder()
                .type(EntityType.PLATFORM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("coin")
    public Entity newCoin(SpawnData data) {
        return entityBuilder()
                .type(EntityType.COIN)
                .from(data)
                .viewWithBBox(new Circle(data.<Integer>get("width") / 2, Color.GOLD))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        return entityBuilder()
                .type(EntityType.DOOR)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("hazard")
    public Entity newHazard(SpawnData data) {
        return entityBuilder()
                .type(EntityType.HAZARD)
                .from(data)
                .build();
    }
    @Spawns("")
    public Entity newBackground(SpawnData data) {
        return entityBuilder()
                .build();
    }
}