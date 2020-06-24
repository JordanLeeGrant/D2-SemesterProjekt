package JumpToBeat.Components;


import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {

    private int _playerJumps,_playerHitpoints,_playerX,_playerY;

    public PlayerComponent(SpawnData data)
    {
        _playerX = (int) data.getX();
        _playerY = (int) data.getY();
        _playerHitpoints = 3;
        _playerJumps = 2;
    }
    @Override
    public void onUpdate(double tpf)
    {
        if (entity.getComponent(PhysicsComponent.class).isOnGround()){
            resetJumps();
        }
    }
    public void movePlayerLeft() {
        entity.getComponent(PhysicsComponent.class).setVelocityX(-200);
      //  entity.getComponent(PlayerAnimationComponent.class).moveplayerLeft();
    }

    public void movePlayerRight() {
        entity.getComponent(PhysicsComponent.class).setVelocityX(200);
        //entity.getComponent(PlayerAnimationComponent.class).moveplayerRight();

    }

    public void playerJump() {
        if (_playerJumps>0) {
            entity.getComponent(PhysicsComponent.class).setVelocityY(-300);
            _playerJumps--;
        }
    }
    public void resetJumps()
    {
        _playerJumps = 2;
    }
    public boolean Damage()
    {
        if(_playerHitpoints>0) {
            _playerHitpoints--;
        }
        else{
            Reset();
        }
        return false;
    }
    public int getPlayerX(){
        return _playerX;
    }
    public int getPlayerY(){
        return _playerY;
    }
    public int get_playerHitpoints(){
        return _playerHitpoints;
    }
    public void Reset()
    {
        entity.setX(0);
        entity.setY(0);
    }
}
