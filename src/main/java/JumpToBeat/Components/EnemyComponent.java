package JumpToBeat.Components;
import JumpToBeat.Entities.EntityType;
import com.almasb.fxgl.dsl.FXGL;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;


public class EnemyComponent extends Component {

    private int enemyX,enemyY,range,damage, health;
    private LocalTimer timer;
    private double velocityY,velocityX;
    boolean flip;

    public EnemyComponent(int xCoord,int yCoord)
    {
        enemyY = yCoord;
        enemyX = xCoord;

    }
    @Override
    public  void onAdded(){
        timer = FXGL.newLocalTimer();
        timer.capture();
        flip = false;
    }
    @Override
    public void onUpdate(double tpf)
    {
        velocityX = entity.getComponent(PhysicsComponent.class).getVelocityX();
        velocityY = entity.getComponent(PhysicsComponent.class).getVelocityY();
        if(entity.getType() == EntityType.ENEMYJUMPER) {
            EnemyJumperAi(); }

        if(entity.getType() == EntityType.ENEMYPATROL) {
            EnemyPatrolAi(); }

        if(entity.getType() == EntityType.ENEMYSEEKER) {
            EnemySeekerAi(); }
    }
    private void EnemySeekerAi (){
        
    }
    private void EnemyJumperAi (){
        if(timer.elapsed(Duration.seconds(3)))
        {
            entity.getComponent(PhysicsComponent.class).setVelocityY(-300);
            timer.capture();
        }
    }
    private void EnemyPatrolAi ()
    {
                        entity.getComponent(PhysicsComponent.class).setVelocityX(-100);
                        entity.getComponent(PhysicsComponent.class).setVelocityX(100);
                        flip= !flip;
    }

}
