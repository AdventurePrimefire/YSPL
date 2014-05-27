package world.grid;

public enum MapLayer {
    /**
     * The Level that Actor are at.
     */
    ActorLevel {
        public RelativeLayer relative(MapLayer layer) {
            switch (layer) {
                case ActorLevel:
                    return RelativeLayer.Equal;
                case FloorLevel:
                case ObjectLevel:
                    return RelativeLayer.Above;
                default:
                    throw new NoRelitiveLevelException();
            }
        }
    },
    /**
     * Where floors and walls are.
     */
    FloorLevel {
        @Override
        public RelativeLayer relative(MapLayer layer) {
            switch (layer) {
                case ActorLevel:
                case ObjectLevel:
                    return RelativeLayer.Below;
                case FloorLevel:
                    return RelativeLayer.Equal;
                default:
                    throw new NoRelitiveLevelException();
            }
        }
    },
    /**
     * Where doors and chests and traps are.
     */
    ObjectLevel {
        @Override
        public RelativeLayer relative(MapLayer layer) {
            switch (layer) {
                case ActorLevel:
                    return RelativeLayer.Below;
                case FloorLevel:
                    return RelativeLayer.Above;
                case ObjectLevel:
                    return RelativeLayer.Equal;
                default:
                    throw new NoRelitiveLevelException();
            }
        }
    };
    /**
     * Determines the current layers position relative to the passed layer.
     * 
     * @param layer
     *            to compare to.
     * @return the relative position to the layer.
     * @throws NoRelitiveLevelException
     *             if the level can not be measured relative to the current one
     */
    public abstract RelativeLayer relative(MapLayer layer);
    
    public enum RelativeLayer {
        Above, Equal, Below;
    }
    
    public class NoRelitiveLevelException extends IllegalArgumentException {
    private static final long serialVersionUID = -3695364708107769942L;
    
    }
}
