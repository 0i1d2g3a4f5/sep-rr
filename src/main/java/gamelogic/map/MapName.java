package gamelogic.map;

/**
 * enum with Map Names
 */
public enum MapName {
    DIZZY_HIGHWAY("dizzy_highway"),
    DEATH_TRAP("death_trap"),
    EXTRA_CRISPY("extra_crispy"),
    LOST_BEARINGS("lost_bearings"),
    TWISTER("twister");


    @Override
    public String toString() {
        return mapName;
    }

    public static MapName fromString(String sName){
        for (MapName mapName:MapName.values()) {
            if(mapName.toString().equals(sName)) return mapName;
        }
        throw new IllegalArgumentException("No map with the Name "+ sName);
    }

    String mapName;
    MapName(String mapName) {
        this.mapName = mapName;
    }

}
