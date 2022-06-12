package gamelogic.game_elements;

import java.io.IOException;

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
    RESTARTPOINT("RestartPoint"),
    EMPTY("Empty"), ;

    @Override
    public String toString() {
        return name;
    }

    public static ElementName parseElementName(String nameString) throws IOException {
        ElementName elementName;
        switch (nameString){
            case "Laser"->{
                elementName = LASER;
            }
            case "CheckPoint" ->{
                elementName = CHECKPOINT;
            }
            case "ConveyorBelt" ->{
                elementName = CONVEYORBELT;
            }
            case "EnergySpace" -> {
                elementName = ENERGYSPACE;
            }
            case "Gear" -> {
                elementName = GEAR;
            }
            case "PushPanel" ->{
                elementName = PUSHPANEL;
            }
            case "StartPoint" ->{
                elementName = STARTPOINT;
            }
            case "Wall" ->{
                elementName = WALL;
            }
            case "Antenna" ->{
                elementName = ANTENNA;
            }
            case "RestartPoint" ->{
                elementName = RESTARTPOINT;
            }
            case "Empty" ->{
                elementName = EMPTY;
            }
            default -> throw new IOException("Element "+nameString+" not found");
        }
        return elementName;
    }

    public static Class parseClass(ElementName elementName) throws IOException {
        Class className;
        switch (elementName){
            case LASER->{
                className = Laser.class;
            }
            case CHECKPOINT -> {
                className = Checkpoint.class;
            }
            case CONVEYORBELT -> {
                className = ConveyorBelt.class;
            }
            case ENERGYSPACE -> {
                className = EnergySpace.class;
            }
            case GEAR -> {
                className = Gear.class;
            }
            case PUSHPANEL -> {
                className = PushPanel.class;
            }
            case PIT -> {
                className = Pit.class;
            }
            case STARTPOINT -> {
                className = StartPoint.class;
            }
            case WALL -> {
                className = Wall.class;
            }
            case ANTENNA -> {
                className = Antenna.class;
            }
            case EMPTY -> {
                className = Empty.class;
            }
            default -> throw new IOException("Class not found");
        }
        return className;
    }

    private String name;



    ElementName(String name) {
        this.name = name;

    }
}
