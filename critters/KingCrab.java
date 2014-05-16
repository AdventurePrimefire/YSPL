import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;


public class KingCrab extends CrabCritter{
public void prossesActors(ArrayList<Actor> actors){
    for (Actor a : actors) {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
    
}
}
