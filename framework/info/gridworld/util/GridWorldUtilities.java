package info.gridworld.util;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class GridWorldUtilities {
public static ArrayList<Location> getEmptyLocations(Grid<Actor> grid) {
    ArrayList<Location> spots = new ArrayList<Location>();
    for (int i = 0; i < grid.getNumRows(); i++) {
        for (int j = 0; j < grid.getNumCols(); j++) {
            Location loc = new Location(i,j);
            if(grid.get(loc)== null){
                spots.add(loc);
            }
        }
    }
    return spots;
}
}
