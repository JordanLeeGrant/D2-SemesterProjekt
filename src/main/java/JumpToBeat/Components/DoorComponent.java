package JumpToBeat.Components;

import com.almasb.fxgl.entity.component.Component;


public class DoorComponent extends Component {
    boolean activestate;
    public DoorComponent()
    {
        activestate = false;
    }

    public void Activate()
    {
        activestate = true;
    }
    public boolean getactivestate()
    {
        return activestate;
    }
}
