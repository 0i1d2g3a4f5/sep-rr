package gamelogic.game_elements;

public enum ElementName {
    LASER("Laser"),
    CHECKPOINT("CheckPoint"),
    CONVEYORBELT("ConveyorBelt"),
    ENERGYSPACE("EnergySpace"),
    GEAR("Gear"),
    PUSHPANEL("PushPanel"),
    PIT("Pit"),
    STARTPOINT("StartPoint"),
    WALL("Wall"),
    ANTENNA("Antenna"),
    EMPTY("Empty");

    @Override
    public String toString() {
        return name;
    }

    private String name;



    ElementName(String name) {
        this.name = name;

    }
}
