package client_package.client_gamelogic;

import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.game_elements.robot.Robot;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mark Ringer
 */
public class CPlayer {
    private boolean self;
    int figure;
    private Client client;
    int EnergyCubes;
    int clientID;
    private Robot robot;
    private Pair<Integer, Card> ownSelectedCard;
    private Pair<Integer, Card> ownToBeRemovedCard;

    private ArrayList<Card> registerCardsOwn = new ArrayList();
    private ArrayList<Card> availableCardsOwn = new ArrayList<>();
    private ArrayList<Boolean> registerCardsOther = new ArrayList<Boolean>();
    private int availableCardsOther;


    public CPlayer(Client client, boolean self){
        this.self=self;
        this.client=client;
        this.figure=client.getFigure();
        this.clientID=client.getId();
        try {
            this.robot=new Robot(figure, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(self){
            for(int i=0; i<5; i++){
                registerCardsOwn.add(i, null);
            }
            for(int i=0; i<9; i++){
                availableCardsOwn.add(i, null);
            }

        }
        else{
            for(int i=0; i<5; i++){
                registerCardsOther.add(i, false);
            }
            availableCardsOther=0;
        }




    }
    public void setAvailableCardsOwn(Card[] input){
        if(input.length != 9){
            throw new RuntimeException("Not 9 available cards.");
        }
        for(int i=0; i<input.length; i++){
            availableCardsOwn.set(i, input[i]);
        }
    }
    public void resetRegisterOwn(){
        for(int i=0; i<5; i++){
            registerCardsOwn.set(i, null);
        }
    }
    public void resetAvailableOwn(){
        for(int i=0; i<9; i++){
            availableCardsOwn.set(i, null);
        }
    }
    public void resetRegisterOther(){
        for(int i=0; i<5; i++){
            registerCardsOther.set(i, false);
        }
    }
    public void resetAvailableOther(){
        availableCardsOther=0;
    }
    public void phaseReset(){
        resetRegisterOwn();
        resetAvailableOwn();

        for(int i=0; i<client.getPlayerList().size(); i++){
            client.getPlayerList().get(i).getPlayer().resetRegisterOther();
            client.getPlayerList().get(i).getPlayer().resetAvailableOther();
        }
        if(client.getClientApplication()!=null) {
            client.getClientApplication().activateAvailableProgrammingSelection(true);
            client.getClientApplication().activateRegisterSelection(true);
        }
    }
    public int selectCardOwn(int position){
        ownSelectedCard = new Pair<>(position, availableCardsOwn.get(position));
        int empty=-1;
        for(int i=0; i<5; i++){
            if(registerCardsOwn.get(i)==null){
                empty=i;
                break;
            }
        }
        return empty;
    }
    public void selectToBeRemovedCardOwn(int position){
        ownToBeRemovedCard = new Pair<>(position, registerCardsOwn.get(position));
    }

    /**
     * returns indexes of cards that are free to choose to register
     * @return
     */
    public ArrayList<Integer> availableIndexes(){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<availableCardsOwn.size(); i++){
            if(availableCardsOwn.get(i)!=null){
                result.add(i);
            }
        }
        return result;
    }
    public void placeSelectedToRegisterOwn(){
        for(int i=0; i<5; i++){
            if(registerCardsOwn.get(i)==null){
                registerCardsOwn.set(i, ownSelectedCard.getValue());
                availableCardsOwn.set(ownSelectedCard.getKey(), null);
                ownSelectedCard=null;
                if(client.getClientApplication()!=null) {
                    client.getClientApplication().activateAvailableProgrammingSelection(true);
                }
                break;
            }
        }
        boolean allFull = true;
        for(int i=0; i<5; i++){
            if(registerCardsOwn.get(i)==null){
                allFull=false;
                break;
            }
        }
        if(allFull){
            if(client.getClientApplication()!=null) {
                client.getClientApplication().activateAvailableProgrammingSelection(false);
                client.getClientApplication().activateRegisterSelection(false);
            }
        }

    }
    public void removeCardFromRegisterOwn(){
        registerCardsOwn.set(ownToBeRemovedCard.getKey(), null);
        for(int i=0; i<availableCardsOwn.size(); i++){
            if(availableCardsOwn.get(i)==null){
                availableCardsOwn.set(i, ownToBeRemovedCard.getValue());
                ownToBeRemovedCard=null;
                if(client.getClientApplication()!=null) {
                    client.getClientApplication().activateRegisterSelection(true);
                }
                break;
            }
        }
    }
    public void updateCardOfRegisterOther(int position, boolean bool){
        registerCardsOther.set(position, bool);
    }
    public void autofill(ArrayList<String> list){
        for(int i=0; i<5; i++){
            if(registerCardsOwn.get(i)==null){
                try {
                    registerCardsOwn.set(i, new CardFactory().createCard(CardName.parseCardName(list.get(0))));
                    list.remove(0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean registerFull(){
        for(int i=0; i<5; i++){
            if(registerCardsOwn.get(i)==null){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Card> getRegisterCardsOwn() {
        return registerCardsOwn;
    }

    public void setRegisterCardsOwn(ArrayList<Card> registerCardsOwn) {
        this.registerCardsOwn = registerCardsOwn;
    }

    public ArrayList<Card> getAvailableCardsOwn() {
        return availableCardsOwn;
    }

    public void setAvailableCardsOwn(ArrayList<Card> availableCardsOwn) {
        this.availableCardsOwn = availableCardsOwn;
    }

    public ArrayList<Boolean> getRegisterCardsOther() {
        return registerCardsOther;
    }

    public void setRegisterCardsOther(ArrayList<Boolean> registerCardsOther) {
        this.registerCardsOther = registerCardsOther;
    }

    public int getAvailableCardsOther() {
        return availableCardsOther;
    }

    public void setAvailableCardsOther(int availableCardsOther) {
        this.availableCardsOther = availableCardsOther;
    }

    public Pair<Integer, Card> getOwnSelectedCard() {
        return ownSelectedCard;
    }

    public void setOwnSelectedCard(Pair<Integer, Card> ownSelectedCard) {
        this.ownSelectedCard = ownSelectedCard;
    }

    public void drawDamage(){
    }








    public int getEnergyCubes() {
        return EnergyCubes;
    }
    public Robot getRobot() {
        return robot;
    }

    @Override
    public String toString() {
        return client.toString();
    }

    public int getClientID() {
        return clientID;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }
}
