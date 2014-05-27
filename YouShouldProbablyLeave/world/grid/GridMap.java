package world.grid;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import world.map.MapActor;
import world.objects.ObjectActor;

/**
 * Our version of the bounded grid that supports multiple layers
 */
// The GridMap is the actor layer
public class GridMap<Actor> extends BoundedGrid<Actor> {
private BoundedGrid<ObjectActor> objectGrid;
private BoundedGrid<MapActor> mapGrid;

public GridMap(int row, int cols) {
    super(row, cols);
    objectGrid = new BoundedGrid<ObjectActor>(row, cols);
    mapGrid = new BoundedGrid<MapActor>(row, cols);
}

@SuppressWarnings("unchecked")
@Override
public Object put(Location loc, Object obj) {
    if (loc instanceof AdvancedLocation) {// We only use advanced locations but
// the framework may put normal locations
        AdvancedLocation aloc = (AdvancedLocation) loc;
        switch (aloc.getLayer()) {
            case ActorLevel:
                if (obj instanceof info.gridworld.actor.Actor) {
                    return super.put(aloc, (Actor) obj);// Warning
                } else {
                    throw new IllegalArgumentException();
                }
            case FloorLevel:
                if (obj.getClass() == MapActor.class) {
                    return this.mapGrid.put(aloc, (MapActor) obj);
                } else {
                    throw new IllegalArgumentException();
                }
            case ObjectLevel:
                if (obj.getClass() == ObjectActor.class) {
                    return this.objectGrid.put(aloc, (ObjectActor) obj);
                } else {
                    throw new IllegalArgumentException();
                }
            default:
                throw new IllegalArgumentException();
        }
    } else {
        if (obj instanceof info.gridworld.actor.Actor) {
            return super.put(loc, (Actor) obj);// Warning
        } else {
            throw new IllegalArgumentException();
        }
    }
}

@Override
public boolean isValid(Location loc) {
    if (loc instanceof AdvancedLocation) {
        switch (((AdvancedLocation) loc).getLayer()) {
            case ActorLevel:
                return super.isValid(loc);
            case FloorLevel:
                return this.mapGrid.isValid(loc);
            case ObjectLevel:
                return this.objectGrid.isValid(loc);
            default:
                throw new IllegalArgumentException();
        }
    } else {
        return super.isValid(loc);
    }
}

public ArrayList<AdvancedLocation> getOccupiedLocation(MapLayer layer) {
    switch (layer) {
        case ActorLevel:
            return this.makeAdvanced(super.getOccupiedLocations(), MapLayer.ActorLevel);
        case FloorLevel:
            return this.makeAdvanced(this.mapGrid.getOccupiedLocations(), MapLayer.FloorLevel);
        case ObjectLevel:
            return this.makeAdvanced(this.objectGrid.getOccupiedLocations(), MapLayer.ObjectLevel);
        default:
            throw new IllegalArgumentException();
    }
}

private ArrayList<AdvancedLocation> makeAdvanced(ArrayList<Location> list, MapLayer layer) {
    ArrayList<AdvancedLocation> out = new ArrayList<AdvancedLocation>();
    for (Location i : list) {
        out.add(new AdvancedLocation(i, layer));
    }
    return out;
}

public Object get(AdvancedLocation loc) {
    switch (loc.getLayer()) {
        case ActorLevel:
            return super.get(loc);
        case FloorLevel:
            return this.mapGrid.get(loc);
        case ObjectLevel:
            return this.objectGrid.get(loc);
        default:
            throw new IllegalArgumentException();
    }
}

public Object remove(AdvancedLocation loc) {
    switch (loc.getLayer()) {
        case ActorLevel:
            return super.remove(loc);
        case FloorLevel:
            return this.mapGrid.remove(loc);
        case ObjectLevel:
            return this.objectGrid.remove(loc);
        default:
            throw new IllegalArgumentException();
    }
}
}
