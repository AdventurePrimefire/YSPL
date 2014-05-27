package world.grid;

import info.gridworld.grid.Location;

/**
 * An extended location to support multiple layers.
 */
public class AdvancedLocation extends Location {
private MapLayer layer;

/**
 * Standard Location constructor
 *
 * @param r
 *            row
 * @param c
 *            column
 */
public AdvancedLocation(int r, int c) {
    super(r, c);
    this.layer = MapLayer.ActorLevel;
}

/**
 * Makes an AdvacedLocaiton from a Location
 *
 * @param loc
 *            location to copy
 */
public AdvancedLocation(Location loc) {
    super(loc.getRow(), loc.getCol());
    this.layer = MapLayer.ActorLevel;
}

/**
 * Location constructor with layer arguments
 *
 * @param r
 *            row
 * @param c
 *            column
 * @param layer
 *            layer of the location
 */
public AdvancedLocation(Location loc, MapLayer layer) {
    super(loc.getRow(), loc.getCol());
    this.layer = layer;
}

/**
 * Gets the map layer of the location
 *
 * @return the map layer
 */
public MapLayer getLayer() {
    return this.layer;
}
}
