package JumpToBeat.Factories;

import JumpToBeat.Components.DoorComponent;
import JumpToBeat.Components.EnemyComponent;
import JumpToBeat.Components.PlayerComponent;
import JumpToBeat.Entities.EntityType;
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
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class JumpToBeatEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent playerPhysics = new PhysicsComponent();
        playerPhysics.setBodyType(BodyType.DYNAMIC);
        playerPhysics.addGroundSensor(new HitBox(BoundingShape.box(22,22)));
        return entityBuilder()
                .type(EntityType.PLAYER)
                .from(data)
                .at(data.getX(),data.getY())
                .view(new Rectangle(20,20,Color.RED))
                .bbox(new HitBox(BoundingShape.box(22,22)))
                .with(new PlayerComponent(data))
                //.with(new PlayerAnimationComponent())
                .with(new CollidableComponent(true))
                .with(playerPhysics)
                .build();
    }
    @Spawns("ENEMYSEEKER")
    public Entity newSeekEnemy(SpawnData data)
    {
        PhysicsComponent enemyPhysics = new PhysicsComponent();
        enemyPhysics.setBodyType(BodyType.DYNAMIC);

        return entityBuilder()
                .at(data.getX(),data.getY())
                .type(EntityType.ENEMYSEEKER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("height"),data.<Integer>get("width"))))
                .view(new Rectangle(data.<Integer>get("height"),data.<Integer>get("width"),Color.RED))
                .with(new EnemyComponent((int)data.getX(),(int)data.getY()))
                .with(new CollidableComponent(true))
                .with(enemyPhysics)
                .build();
    }
    @Spawns("ENEMYJUMPER")
    public Entity newJumpEnemy(SpawnData data)
    {
        PhysicsComponent enemyPhysics = new PhysicsComponent();
        enemyPhysics.setBodyType(BodyType.DYNAMIC);

        return entityBuilder()
                .at(data.getX(),data.getY())
                .type(EntityType.ENEMYJUMPER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("height"),data.<Integer>get("width"))))
                .view(new Rectangle(data.<Integer>get("height"),data.<Integer>get("width"),Color.RED))
                .with(new EnemyComponent((int)data.getX(),(int)data.getY()))
                .with(new CollidableComponent(true))
                .with(enemyPhysics)
                .build();
    }
    @Spawns("ENEMYPATROL")
    public Entity newPatrolEnemy(SpawnData data)
    {
        PhysicsComponent enemyPhysics = new PhysicsComponent();
        enemyPhysics.setBodyType(BodyType.KINEMATIC);

        return entityBuilder()
                .at(data.getX(),data.getY())
                .type(EntityType.ENEMYPATROL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("height"),data.<Integer>get("width"))))
                .view(new Rectangle(data.<Integer>get("height"),data.<Integer>get("width"),Color.RED))
                .with(new EnemyComponent((int)data.getX(),(int)data.getY()))
                .with(new CollidableComponent(true))
                .with(enemyPhysics)
                .build();
    }
    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return entityBuilder()
                .type(EntityType.PLATFORM)
                .from(data)
                .at(data.getX(),data.getY())
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .viewWithBBox(new Rectangle(data.<Integer>get("width"),data.<Integer>get("height"),Color.GREY))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("coin")
    public Entity newCoin(SpawnData data) {
        return entityBuilder()
                .type(EntityType.COIN)
                .from(data)
                .at(data.getX(),data.getY())
                .viewWithBBox(new Circle(15,15,15, Color.GOLD))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        Rectangle doorBox = new Rectangle(100,200,Color.BLACK);
        doorBox.setOpacity(0.5);

        return entityBuilder()
                .type(EntityType.DOOR)
                .at(50,200)
                .bbox(new HitBox(BoundingShape.box(100, 300)))
                .view(doorBox)
                .with(new DoorComponent())
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
}