package newmessages;
/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */

public class MessageTypeFactory {

    /**
     * @param string
     * @return
     */
    public MessageType fromString(String string) {
        switch (string) {
            case "GameStarted" ->{
                return MessageType.GAME_STARTED;
            }
            case "ActivePhase" -> {
                return MessageType.ACTIVE_PHASE;
            }
            case "Alive" -> {
                return MessageType.ALIVE;
            }
            case "Animation" -> {
                return MessageType.ANIMATION;
            }
            case "CardPlayed" -> {
                return MessageType.CARD_PLAYED;
            }
            case "CardSelected" -> {
                return MessageType.CARD_SELECTED;
            }
            case "CheckPointReached" -> {
                return MessageType.CHECKPOINT_REACHED;
            }
            case "CurrentPlayer" -> {
                return MessageType.CURRENT_PLAYER;
            }
            case "Energy" -> {
                return MessageType.ENERGY;
            }
            case "Error" -> {
                return MessageType.ERROR;
            }
            case "FigureUnavailable" -> {
                return MessageType.FIGURE_UNAVAILABLE;
            }
            case "GameFinished" -> {
                return MessageType.GAME_FINISHED;
            }
            case "HashedCode" -> {
                return MessageType.HASHED_CODE;
            }
            case "HelloClient" -> {
                return MessageType.HELLO_CLIENT;
            }
            case "HelloServer" -> {
                return MessageType.HELLO_SERVER;
            }
            case "MapSelected" -> {
                return MessageType.MAP_SELECTED;
            }
            case "Movement" -> {
                return MessageType.MOVEMENT;
            }
            case "NameUnavailable" -> {
                return MessageType.NAME_UNAVAILABLE;
            }
            case "NotYourCards" -> {
                return MessageType.NOT_YOUR_CARDS;
            }
            case "PlayCard" -> {
                return MessageType.PLAY_CARD;
            }
            case "PlayerAdded" -> {
                return MessageType.PLAYER_ADDED;
            }
            case "PlayerStatus" -> {
                return MessageType.PLAYER_STATUS;
            }
            case "PlayerTurning" -> {
                return MessageType.PLAYER_TURNING;
            }
            case "PlayerValues" -> {
                return MessageType.PLAYER_VALUES;
            }case "Reboot" -> {
                return MessageType.REBOOT;
            }case "RebootDirection" -> {
                return MessageType.REBOOT_DIRECTION;
            }case "ReceivedChat" -> {
                return MessageType.RECEIVED_CHAT;
            }case "Reconnect" -> {
                return MessageType.RECONNECT;
            }
            case "SelectedCard" -> {
                return MessageType.SELECTED_CARD;
            }case "SelectionFinished" -> {
                return MessageType.SELECTION_FINISHED;
            }case "SelectMap" -> {
            return MessageType.SELECT_MAP;
            }case "SendChat" -> {
                return MessageType.SEND_CHAT;
            }case "SetStartingPoint" -> {
                return MessageType.SET_STARTING_POINT;
            }case "SetStatus" -> {
                return MessageType.SET_STATUS;
            }
            case "ShuffleCoding" -> {
                return MessageType.SHUFFLE_CODING;
            }
            case "StartingPointTaken" -> {
                return MessageType.STARTING_POINT_TAKEN;
            }case "TimerEnded" -> {
                return MessageType.TIMER_ENDED;
            }
            case "TimerStarted" -> {
                return MessageType.TIMER_STARTED;
            }
            case "ValuesAccepted" -> {
                return MessageType.VALUES_ACCEPTED;
            }
            case "Welcome" -> {
                return MessageType.WELCOME;
            }
            case "WrongName" -> {
                return MessageType.WRONG_NAME;
            }
            case "WrongPass" -> {
                return MessageType.WRONG_PASS;
            }
            default -> {
                return MessageType.DEFAULT;
            }


        }
    }
}
