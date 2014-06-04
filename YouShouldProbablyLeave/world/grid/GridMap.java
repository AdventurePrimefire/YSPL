package world.grid;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Our version of the bounded grid that supports multiple layers @
 */
// The GridMap is the actor layer
public class GridMap<Actor> extends BoundedGrid<Actor> {
    private BoundedGrid<Actor> objectGrid;
    private BoundedGrid<Actor> mapGrid;

    public GridMap(int row, int cols) {
        super(row, cols);
        objectGrid = new BoundedGrid<Actor>(row, cols);
        mapGrid = new BoundedGrid<Actor>(row, cols);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Actor put(Location loc, Actor obj) {
        if (loc instanceof AdvancedLocation) {// We only use advanced locations
// but
// the framework may put normal locations
            AdvancedLocation aloc = (AdvancedLocation) loc;
            switch (aloc.getLayer()) {
                case ActorLevel:
                    if (obj instanceof info.gridworld.actor.Actor) {
                        return super.put(aloc, obj);// Warning
                    } else {
                        throw new IllegalArgumentException();
                    }
                case FloorLevel:
                    if (obj instanceof info.gridworld.actor.Actor) {
                        return this.mapGrid.put(aloc, obj);
                    } else {
                        throw new IllegalArgumentException();
                    }
                case ObjectLevel:
                    if (obj instanceof info.gridworld.actor.Actor) {
                        return this.objectGrid.put(aloc, obj);
                    } else {
                        throw new IllegalArgumentException();
                    }
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            if (obj instanceof info.gridworld.actor.Actor) {
                return super.put(loc, obj);// Warning
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

    public Actor get(AdvancedLocation loc) {
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

    public Actor remove(AdvancedLocation loc) {
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

    public ArrayList<AdvancedLocation> getAllOccupiedLoactions() {
        ArrayList<AdvancedLocation> floor = new ArrayList<AdvancedLocation>();
        for (Location i : this.mapGrid.getOccupiedLocations()) {
            floor.add(new AdvancedLocation(i, MapLayer.FloorLevel));
        }
        ArrayList<AdvancedLocation> objects = new ArrayList<AdvancedLocation>();
        for (Location i : this.objectGrid.getOccupiedLocations()) {
            objects.add(new AdvancedLocation(i, MapLayer.ObjectLevel));
        }
        ArrayList<AdvancedLocation> actors = new ArrayList<AdvancedLocation>();
        for (Location i : super.getOccupiedLocations()) {
            actors.add(new AdvancedLocation(i, MapLayer.ActorLevel));
        }
        ArrayList<AdvancedLocation> all = new ArrayList<AdvancedLocation>(floor.size() + objects.size() + actors.size());
        for (AdvancedLocation i : actors) {
            all.add(i);
        }
        for (AdvancedLocation i : objects) {
            for (AdvancedLocation j : all) {
                if (i.getCol() != j.getCol() && i.getRow() != j.getRow()) {
                    all.add(i);
                }
            }
        }
        for (AdvancedLocation i : floor) {
            int size = all.size();
            for (int j = 0; j < size; j++) {
                if (i.getCol() != all.get(j).getCol() && i.getRow() != all.get(j).getRow()) {
                    all.add(i);
                }
            }
        }
        return all;
    }

    public BoundedGrid<Actor> getLayer(MapLayer layer) {
        switch (layer) {
            case ActorLevel:
                return this;
            case FloorLevel:
                return this.mapGrid;
            case ObjectLevel:
                return this.objectGrid;
            default:
                return null;

        }
    }
}
