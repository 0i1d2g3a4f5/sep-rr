package server_package.gamelogic.map;

public enum MapName {
    DIZZY_HIGHWAY("dizzy_highway");


    @Override
    public String toString() {
        return mapName;
    }

    String mapName;
    MapName(String mapName) {
        this.mapName = mapName;
    }

}
