package gamelogic.cards;

import gamelogic.cards.damage_card.TrojanHorse;
import server_package.Server;

import java.io.IOException;

public enum CardName {
    TURN_LEFT_CARD("TurnLeft"),

    // Damage Cards
    SPAM("Spam"),
    TROJAN_HORSE("TrojanHorse"),
    VIRUS("Virus"),
    WORM("Worm"),

    // Programming Cards
    AGAIN("Again"),
    BACK_UP("BackUp"),
    LEFT_TURN("LeftTurn"),
    MOVE_ONE("MoveOne"),
    MOVE_TWO("MoveTwo"),
    MOVE_THREE("MoveThree"),
    POWER_UP("PowerUp"),
    RIGHT_TURN("RightTurn"),
    U_TURN("UTurn"),

    // Permanent Upgrade Cards
    ADMIN_PRIVILEGE("AdminPrivilege"),
    CORRUPTION_WAVE("CorruptionWave"),
    BLUE_SCREEN("BlueScreen"),
    CRAB_LEGS("CrabLegs"),
    BRAKES("Brakes"),
    DEFLECTOR_SHIELD("DeflectorShield"),
    CACHE_MEMORY("CacheMemory"),
    DEFRAG_GIZMO("DefragGizmo"),
    DOUBLE_BARREL_LASER("DoubleBarrelLaser"),
    MODULAR_CHASSIS("ModularChassis"),
    FIREWALL("Firewall"),
    PRESSOR_BEAM("PressorBeam"),
    HOVER_UNIT("HoverUnit"),
    RAIL_GUN("RailGun"),
    MEMORY_STICK("MemoryStick"),
    RAMMING_GEAR("RammingGear"),
    MINI_HOWITZER("MiniHowitzer"),
    REAR_LASER("RearLaser"),
    SCRAMBLER("Scrambler"),
    TRACTOR_BEAM("TractorBeam"),
    SIDE_ARMS("SideArms"),
    TROJAN_NEEDLER("TrojanNeedler"),
    TELEPORTER("Teleporter"),
    VIRUS_MODULE("VirusModule"),

    // Temporary Upgrade Cards
    BOINK("Boink"),
    HACK("Hack"),
    ENERGY_ROUTINE("EnergyRoutine"),
    MANUAL_SORT("ManualSort"),
    MEMORY_SWAP("MemorySwap"),
    REBOOST("Reboost"),
    RECHARGE("Recharge"),
    RECOMPILE("Recompile"),
    SPAM_FOLDER_ROUTINE("SpamFolderRoutine"),
    REFRESH("Refresh"),
    ZOOP("Zoop"),
    SPEED_ROUTINE("SpeedRoutine"),
    SPAM_FOLDER("SpamFolder"),
    REPEAT_ROUTINE("RepeatRoutine"),
    SANDBOX_ROUTINE("SandboxRoutine"),
    WEASEL_ROUTINE("WeaselRoutine"),
    SPAM_BLOCKER("SpamBlocker"),
    NULL("Null");



    private String cardName;
    public static CardName parseCardName(String cardName) {
        for (CardName  name:CardName.values()){
            cardName = cardName.replaceAll("\"","");
            //Server.serverLogger.info("Wanted: " + cardName);
            //Server.serverLogger.info("Actual: " + name.toString());

            if(name.toString().equalsIgnoreCase(cardName)) return name;
        }
        throw new IllegalArgumentException("Card " + cardName + " not found");
    }
    public String toString(){
        return cardName;
    }
    CardName(String cardName) {
        this.cardName = cardName;
    }


}
