package messages;

import com.google.gson.JsonObject;
/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */

public class MessageFactory {

    /**
     * @param messageType
     * @param jsonObject
     * @return
     */
    public Message createMessage(MessageType messageType, JsonObject jsonObject){
        switch (messageType){
            case GAME_STARTED ->{
                return new MessageGameStarted(jsonObject);
            }
            case ACTIVE_PHASE -> {
                return new MessageActivePhase(jsonObject);
            }
            case ALIVE -> {
                return new MessageAlive(jsonObject);
            }
            case ANIMATION -> {
                return new MessageAnimation(jsonObject);
            }
            case CARD_PLAYED -> {
                return new MessageCardPlayed(jsonObject);
            }
            case CARD_SELECTED -> {
                return new MessageCardSelected(jsonObject);
            }
            case CHECKPOINT_REACHED -> {
                return new MessageCheckPointReached(jsonObject);
            }
            case CURRENT_PLAYER -> {
                return new MessageCurrentPlayer(jsonObject);
            }
            case ENERGY -> {
                return new MessageEnergy(jsonObject);
            }
            case ERROR -> {
                return new MessageError(jsonObject);
            }
            case FIGURE_UNAVAILABLE -> {
                return new MessageFigureUnavailable(jsonObject);
            }
            case GAME_FINISHED -> {
                return new MessageGameFinished(jsonObject);
            }
            case HASHED_CODE -> {
                return new MessageHashedCode(jsonObject);
            }
            case HELLO_CLIENT -> {
                return new MessageHelloClient(jsonObject);
            }
            case HELLO_SERVER -> {
                return new MessageHelloServer(jsonObject);
            }
            case MAP_SELECTED -> {
                return new MessageMapSelected(jsonObject);
            }
            case MOVEMENT -> {
                return new MessageMovement(jsonObject);
            }
            case NAME_UNAVAILABLE -> {
                return new MessageNameUnavailable(jsonObject);
            }
            case NOT_YOUR_CARDS -> {
                return new MessageNotYourCards(jsonObject);
            }
            case PLAY_CARD -> {
                return new MessagePlayCard(jsonObject);
            }
            case PLAYER_ADDED -> {
                return new MessagePlayerAdded(jsonObject);
            }
            case PLAYER_STATUS -> {
                return new MessagePlayerStatus(jsonObject);
            }
            case PLAYER_TURNING -> {
                return new MessagePlayerTurning(jsonObject);
            }
            case PLAYER_VALUES -> {
                return new MessagePlayerValues(jsonObject);
            }
            case REBOOT -> {
                return new MessageReboot(jsonObject);
            }
            case REBOOT_DIRECTION -> {
                return new MessageRebootDirection(jsonObject);
            }
            case RECEIVED_CHAT -> {
                return new MessageReceivedChat(jsonObject);
            }
            case RECONNECT -> {
                return new MessageReconnect(jsonObject);
            }
            case SELECTED_CARD -> {
                return new MessageSelectedCard(jsonObject);
            }
            case SELECTION_FINISHED -> {
                return new MessageSelectionFinished(jsonObject);
            }
            case SELECT_MAP -> {
                return new MessageSelectMap(jsonObject);
            }
            case SEND_CHAT -> {
                return new MessageSendChat(jsonObject);
            }
            case SET_STARTING_POINT -> {
                return new MessageSetStartingPoint(jsonObject);
            }
            case SET_STATUS -> {
                return new MessageSetStatus(jsonObject);
            }
            case SHUFFLE_CODING -> {
                return new MessageShuffleCoding(jsonObject);
            }
            case STARTING_POINT_TAKEN -> {
                return new MessageStartingPointTaken(jsonObject);
            }
            case TIMER_ENDED -> {
                return new MessageTimerEnded(jsonObject);
            }
            case TIMER_STARTED -> {
                return new MessageTimerStarted(jsonObject);
            }
            case VALUES_ACCEPTED -> {
                return new MessageValuesAccepted(jsonObject);
            }
            case WELCOME -> {
                return new MessageWelcome(jsonObject);
            }
            case WRONG_NAME -> {
                return new MessageWrongName(jsonObject);
            }
            case WRONG_PASS -> {
                return new MessageWrongPass(jsonObject);
            }
            case YOUR_CARDS -> {
                return new MessageYourCards(jsonObject);
            }
            case CURRENT_CARDS -> {
                return new MessageCurrentCards(jsonObject);
            }
            case DRAW_DAMAGE -> {
                return new MessageDrawDamage(jsonObject);
            }
            case CARDS_YOU_GOT_NOW -> {
                Message.messageLogger.debug("Created new CardsYouGotNow");
                MessageCardsYouGotNow message = new MessageCardsYouGotNow(jsonObject);
                Message.messageLogger.warn("message "+ message);
                return message;

            }
            case REPLACE_CARD -> {
                return new MessageReplaceCard(jsonObject);
            }
            default -> throw new IllegalArgumentException("There is no MessageType "+messageType);
        }

    }
}
