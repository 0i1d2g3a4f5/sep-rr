package gamelogic.map;

public enum MapName {
    DIZZY_HIGHWAY("dizzy_highway"),
    DEATH_TRAP("death_trap"),
    EXTRA_CRISPY("extra_crispy"),
    LOST_BEARINGS("lost_bearings");


    @Override
    public String toString() {
        return mapName;
    }

    String mapName;
    MapName(String mapName) {
        this.mapName = mapName;
    }

}
