package client_package.client_gamelogic.game_elements;

import java.io.IOException;

public enum ElementName {
    LASER("Laser"),
    LASERBEAM("LaserBeam"),
    CHECKPOINT("CheckPoint"),
    CONVEYORBELT("ConveyorBelt"),
    ENERGYSPACE("EnergySpace"),
    GEAR("Gear"),
    PUSHPANEL("PushPanel"),
    PIT("Pit"),
    STARTPOINT("StartPoint"),
    WALL("Wall"),
    ROBOT("Robot"),
    ANTENNA("Antenna"),
    RESTARTPOINT("RestartPoint"),
    ENERGY_CUBE("EnergyCube"),
    EMPTY("Empty");

    @Override
    public String toString() {
        return name;
    }

    /**
     * parses string to ElementName
     * @param nameString
     * @return
     * @throws IOException
     * @author Mark Ringer
     */

    public static ElementName parseElementName(String nameString) throws IOException {
        //ElementName elementName;
        for (ElementName elementName:ElementName.values()) {
            if(elementName.toString().equalsIgnoreCase(nameString)) return elementName;
        }
        throw new IOException("Element "+nameString+" not found");
    }
    private String name;

    ElementName(String name) {
        this.name = name;

    }
}
