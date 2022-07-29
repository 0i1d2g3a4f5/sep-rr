package client_package.client_gamelogic.cards;

import server_package.Server;

import java.io.IOException;

/**
 * @author Vivian Kafadar
 */
public enum CardName {
    // Damage Cards
    SPAM("Spam"),
    TROJAN_HORSE("TrojanHorse"),
    VIRUS("Virus"),
    WORM("Worm"),
    // Programming Cards
    AGAIN("Again"),
    BACK_UP("BackUp"),
    TURNLEFT("TurnLeft"),
    MOVEI("MoveI"),
    MOVEII("MoveII"),
    MOVEIII("MoveIII"),
    POWER_UP("PowerUp"),
    TURNRIGHT("TurnRight"),
    UTURN("UTurn"),
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
    UNKNOWN("Unknown"),
    NULL("Null");

    private String cardName;

    /**
     * @param cardName
     * @return
     * @throws IOException
     */
    public static CardName parseCardName(String cardName) throws IOException {
        for (CardName name:CardName.values()){
            cardName = cardName.replaceAll("\"","");
            if(name.toString().equalsIgnoreCase(cardName)) return name;
        }
        throw new IOException("Card " + cardName + " not found");
    }

    public String toString(){
        return cardName;
    }

    CardName(String cardName) {
        this.cardName = cardName;
    }
}
