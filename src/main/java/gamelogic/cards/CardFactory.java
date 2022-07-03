package gamelogic.cards;

import gamelogic.cards.damage_card.Spam;
import gamelogic.cards.damage_card.TrojanHorse;
import gamelogic.cards.damage_card.Virus;
import gamelogic.cards.damage_card.Worm;
import gamelogic.cards.programming_cards.*;
import gamelogic.cards.special_cards.*;
import gamelogic.cards.upgrade_cards.permanent.*;
import gamelogic.cards.upgrade_cards.temporary.*;

import java.io.IOException;

/**
 * @author Mark Ringer, Qinyi
 */
public class CardFactory {

    public Card createCard(CardName cardName) throws IOException {
        return buildCard(cardName);
    }
    private Card buildCard(CardName cardName) throws IOException {
        switch (cardName){
            case SPAM -> {
                return new Spam();
            }
            case TROJAN_HORSE -> {
                return new TrojanHorse();
            }
            case VIRUS -> {
                return new Virus();
            }
            case WORM -> {
                return new Worm();
            }
            case AGAIN -> {
                return new Again();
            }
            case BACK_UP -> {
                return new BackUp();
            }
            case LEFT_TURN -> {
                return new LeftTurn();
            }
            case MOVE_ONE -> {
                return new MoveOne();
            }
            case MOVE_TWO -> {
                return new MoveTwo();
            }
            case MOVE_THREE -> {
                return new MoveThree();
            }
            case POWER_UP -> {
                return new PowerUp();
            }
            case RIGHT_TURN -> {
                return new RightTurn();
            }
            case U_TURN -> {
                return new UTurn();
            }
            case ADMIN_PRIVILEGE -> {
                return new AdminPrivilege();
            }
            case CORRUPTION_WAVE -> {
                return new CorruptionWave();
            }
            case BLUE_SCREEN -> {
                return new BlueScreenOfDeath();
            }
            case CRAB_LEGS -> {
                return new CrabLegs();
            }
            case BRAKES -> {
                return new Brakes();
            }
            case DEFLECTOR_SHIELD -> {
                return new DeflectorShield();
            }
            case CACHE_MEMORY -> {
                return new CacheMemory();
            }
            case DEFRAG_GIZMO -> {
                return new DefragGizmo();
            }
            case DOUBLE_BARREL_LASER -> {
                return new DoubleBarrelLaser();
            }
            case MODULAR_CHASSIS -> {
                return new ModularChassis();
            }
            case FIREWALL -> {
                return new Firewall();
            }
            case PRESSOR_BEAM -> {
                return new PressorBeam();
            }
            case HOVER_UNIT -> {
                return new HoverUnit();
            }
            case RAIL_GUN -> {
                return new RailGun();
            }
            case MEMORY_STICK -> {
                return new MemoryStick();
            }
            case RAMMING_GEAR -> {
                return new RammingGear();
            }
            case MINI_HOWITZER -> {
                return new MiniHowitzer();
            }
            case REAR_LASER -> {
                return new RearLaser();
            }
            case SCRAMBLER -> {
                return new Scrambler();
            }
            case TRACTOR_BEAM -> {
                return new TractorBeam();
            }
            case SIDE_ARMS -> {
                return new SideArms();
            }
            case TROJAN_NEEDLER -> {
                return new TrojanNeedler();
            }
            case TELEPORTER -> {
                return new Teleporter();
            }
            case VIRUS_MODULE -> {
                return new VirusModule();
            }
            case BOINK -> {
                return new Boink();
            }
            case HACK -> {
                return new Hack();
            }
            case ENERGY_ROUTINE -> {
                return new EnergyRoutine();
            }
            case MANUAL_SORT -> {
                return new ManualSort();
            }
            case MEMORY_SWAP -> {
                return new MemorySwap();
            }
            case REBOOST -> {
                return new Reboost();
            }
            case RECHARGE -> {
                return new Recharge();
            }
            case RECOMPILE -> {
                return new Recompile();
            }
            case SPAM_FOLDER_ROUTINE -> {
                return new SpamFolderRoutine();
            }
            case REFRESH -> {
                return new Refresh();
            }
            case ZOOP -> {
                return new Zoop();
            }
            case SPEED_ROUTINE -> {
                return new SpeedRoutine();
            }
            case SPAM_FOLDER -> {
                return new SpamFolder();
            }
            case REPEAT_ROUTINE -> {
                return new RepeatRoutine();
            }
            case SANDBOX_ROUTINE -> {
                return new SandboxRoutine();
            }
            case WEASEL_ROUTINE -> {
                return new WeaselRoutine();
            }
            case SPAM_BLOCKER -> {
                return new SpamBlocker();
            }

            default -> throw new IOException("Card "+cardName+" not found");
        }
    }
}
