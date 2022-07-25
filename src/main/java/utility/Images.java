package utility;

import gamelogic.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Erdogan
 * replaces image paths with enums
 */
public enum Images {
    /*
    public void rotate(ImageView imageView, int direction){
        imageView.setRotate(imageView.getRotate()+direction*90);
    }
    //TODO edit*/

    //cards
    YESCARD("/YesCard.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/YesCard.png")))), "Card"),
    NOCARD("/NoCard.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/NoCard.png")))), "Card"),
    CARD_BACK_CARD("/CardBack.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CardBack.png")))), "Card"),

    //programmingCards
    AGAIN_CARD("/Again.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Again.png")))), "Card"),
    BACK_UP_CARD("/BackUp.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BackUp.png")))), "Card"),
    MOVE_1_CARD("/Move1.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Move1.png")))), "Card"),
    MOVE_2_CARD("/Move2.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Move2.png")))), "Card"),
    MOVE_3_CARD("/Move3.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Move3.png")))), "Card"),
    POWER_UP_CARD("/PowerUp.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PowerUp.png")))), "Card"),
    TURN_LEFT_CARD("/TurnLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/TurnLeft.png")))), "Card"),
    TURN_RIGHT_CARD("/TurnRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/TurnRight.png")))), "Card"),
    U_TURN_CARD("/UTurn.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/UTurn.png")))), "Card"),

    //damageCards
    SPAM_CARD("/Spam.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Spam.png")))), "Card"),
    TROJAN_HORSE_CARD("/TrojanHorse.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/TrojanHorse.png")))), "Card"),
    VIRUS_CARD("/Virus.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Virus.png")))), "Card"),
    WORM_CARD("/Worm.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Worm.png")))), "Card"),

    //specialProgrammingCards
    ENERGY_ROUTINE_CARD("/EnergyRoutine.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/EnergyRoutine.png")))), "Card"),
    REPEAT_ROUTINE_CARD("/RepeatRoutine.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RepeatRoutine.png")))), "Card"),
    SANDBOX_ROUTINE_CARD("/SandboxRoutine.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/SandboxRoutine.png")))), "Card"),
    SPAM_FOLDER_CARD("/SpamFolder.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/SpamFolder.png")))), "Card"),
    SPEED_ROUTINE_CARD("/SpeedRoutine.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/SpeedRoutine.png")))), "Card"),
    WEASEL_ROUTINE_CARD("/WeaselRoutine.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/WeaselRoutine.png")))), "Card"),

    //board elements
    SELF_HIGHLIGHT("/SelfHighlight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/SelfHighlight.png")))), "MapElement" ),
    //antennae
    ANTENNA_EAST_ELEMENT("/AntennaEast.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaEast.png")))), "MapElement"),
    ANTENNA_NORTH_ELEMENT("/AntennaNorth.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaNorth.png")))), "MapElement"),
    ANTENNA_SOUTH_ELEMENT("/AntennaSouth.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaSouth.png")))), "MapElement"),
    ANTENNA_WEST_ELEMENT("/AntennaWest.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaWest.png")))), "MapElement"),

    //blue conveyor belts
    BELT_BLUE_CROSS_DOWN_ELEMENT("/BeltBlueCrossDown.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueCrossDown.png")))), "MapElement"),
    BELT_BLUE_DOWN_ELEMENT("/BeltBlueDown.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueDown.png")))), "MapElement"),
    BELT_BLUE_LEFT_ELEMENT("/BeltBlueLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueLeft.png")))), "MapElement"),
    BELT_BLUE_RIGHT_ELEMENT("/BeltBlueRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueRight.png")))), "MapElement"),
    BELT_BLUE_RIGHT_TO_UP("/BeltBlueRightToUp.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueRightToUp.png")))), "MapElement"),
    BELT_BLUE_UP_ELEMENT("/BeltBlueTop.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueTop.png")))), "MapElement"),
    BELT_BLUE_LEFT_TO_BOTTOM("/BeltBlueLeftToBottom.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueLeftToBottom.png")))), "MapElement"),
    BELT_BLUE_RIGHT_TO_BOTTOM("/BeltBlueRightToBottom.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueRightToBottom.png")))), "MapElement"),
    BELT_BLUE_TOP_TO_RIGHT_ELEMENT("/BeltBlueTopToRight.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueTopToRight.png")))), "MapElement"),
    BELT_BLUE_LEFT_TO_UP_ELEMENT("/BeltBlueLeftToUp.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueLeftToUp.png")))), "MapElement"),
    BELT_BLUE_BOTTOM_TO_LEFT_ELEMENT("/BeltBlueBottomToLeft.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueBottomToLeft.png")))), "MapElement"),
    BELT_BLUE_TOP_TO_LEFT_ELEMENT("/BeltBlueTopToLeft.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueTopToLeft.png")))), "MapElement"),
    BELT_BLUE_BOTTOM_TO_RIGHT_ELEMENT("/BeltBlueBottomToRight.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueBottomToRight.png")))), "MapElement"),
    BELT_BLUE_BOTTOM_RIGHT_TO_LEFT_ELEMENT("/CBBlueBottomRightToLeft.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueBottomRightToLeft.png")))), "MapElement"),
    BELT_BLUE_TOP_RIGHT_TO_BOTTOM_ELEMENT("/CBBlueTopRightToBottom.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueTopRightToBottom.png")))), "MapElement"),
    BELT_BLUE_BOTTOM_LEFT_TO_TOP_ELEMENT("/CBBlueBottomLeftToTop.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueBottomLeftToTop.png")))), "MapElement"),
    BELT_BLUE_TOP_LEFT_TO_RIGHT_ELEMENT("/CBBlueTopLeftToRight.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueTopLeftToRight.png")))), "MapElement"),
    BELT_BLUE_BOTTOM_LEFT_TO_RIGHT_ELEMENT("/CBBlueBottomLeftToRight.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueBottomLeftToRight.png")))), "MapElement"),
    BELT_BLUE_BOTTOM_RIGHT_TO_TOP_ELEMENT("/CBBlueBottomRightToTop.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueBottomRightToTop.png")))), "MapElement"),
    BELT_BLUE_TOP_LEFT_TO_BOTTOM_ELEMENT("/CBBlueTopLeftToBottom.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueTopLeftToBottom.png")))), "MapElement"),
    BELT_BLUE_TOP_RIGHT_TO_LEFT_ELEMENT("/CBBlueTopRightToLeft.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CBBlueTopRightToLeft.png")))), "MapElement"),

    //green conveyor belts

    BELT_GREEN_LEFT_ELEMENT("/BeltGreenLeft.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenLeft.png")))), "MapElement"),
    BELT_GREEN_RIGHT_ELEMENT("/BeltGreenRight.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenRight.png")))), "MapElement"),
    BELT_GREEN_BOTTOM_ELEMENT("/BeltGreenDown.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenDown.png")))), "MapElement"),
    BELT_GREEN_UP_ELEMENT("/BeltGreenUp.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenUp.png")))), "MapElement"),
    BELT_GREEN_RIGHT_TO_UP_ELEMENT("/BeltGreenRightToUp.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenRightToUp.png")))), "MapElement"),
    BELT_GREEN_LEFT_TO_UP_ELEMENT("/BeltGreenLeftToUp.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenLeftToUp.png")))), "MapElement"),
    BELT_GREEN_LEFT_TO_BOTTOM_ELEMENT("/BeltGreenLeftToBottom.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenLeftToBottom.png")))), "MapElement"),
    BELT_GREEN_RIGHT_TO_BOTTOM_ELEMENT("/BeltGreenRightToBottom.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenRightToBottom.png")))), "MapElement"),
    BELT_GREEN_BOTTOM_TO_LEFT_ELEMENT("/BeltGreenBottomToLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenBottomToLeft.png")))), "MapElement"),
    BELT_GREEN_TOP_TO_LEFT_ELEMENT("/BeltGreenTopToLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenUpToLeft.png")))), "MapElement"),
    BELT_GREEN_TOP_TO_RIGHT_ELEMENT("/BeltGreenTopToRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenUpToRight.png")))), "MapElement"),
    BELT_GREEN_BOTTOM_TO_RIGHT_ELEMENT("/BeltGreenBottomToRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenBottomToRight.png")))), "MapElement"),
    BELT_2X8_ELEMENT("/Board2x8.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Board2x8.png")))), "MapElement"),

    //checkpoints
    CHECKPOINT_1_ELEMENT("/CheckPoint1.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint1.png")))), "MapElement"),
    CHECKPOINT_2_ELEMENT("/CheckPoint2.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint2.png")))), "MapElement"),
    CHECKPOINT_3_ELEMENT("/CheckPoint3.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint3.png")))), "MapElement"),
    CHECKPOINT_4_ELEMENT("/CheckPoint4.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint4.png")))), "MapElement"),
    ENERGY_SPACE_GREEN_ELEMENT("/EnergySpaceGreen.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/EnergySpaceGreen.png")))), "MapElement"),
    ENERGY_CUBE("/EnergyCube.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/EnergyCube.png")))), "MapElement"),
    GEAR_CLOCKWISE_ELEMENT("/GearClockwise.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/GearClockwise.png")))), "MapElement"),
    GEAR_COUNTERCLOCKWISE_ELEMENT("/GearCounterclockwise.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/GearCounterclockwise.png")))), "MapElement"),
    LASER_DOWN_ELEMENT("/LaserDown.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserDown.png")))), "MapElement"),
    LASER_LEFT_ELEMENT("/LaserLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserLeft.png")))), "MapElement"),
    LASER_RIGHT_ELEMENT("/LaserRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserRight.png")))), "MapElement"),
    LASER_UP_ELEMENT("/LaserUp.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserUp.png")))), "MapElement"),
    LASER_BEAM_HORI("/LaserBeam.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserBeam.png")))), "MapElement"),
    LASER_BEAM_VERTI("/LaserBeamI.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserBeamI.png")))), "MapElement"),
    PIT_ELEMENT("/Pit.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Pit.png")))), "MapElement"),
    PUSH_PANEL_24_BOTTOM_ELEMENT("/PushPanel24Bottom.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel24Bottom.png")))), "MapElement"),
    PUSH_PANEL_24_LEFT_ELEMENT("/PushPanel24Left.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel24Left.png")))), "MapElement"),
    PUSH_PANEL_24_RIGHT_ELEMENT("/PushPanel24Right.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel24Right.png")))), "MapElement"),
    PUSH_PANEL_24_TOP_ELEMENT("/PushPanel24Top.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel24Top.png")))), "MapElement"),
    PUSH_PANEL_135_BOTTOM_ELEMENT("/PushPanel135Bottom.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel135Bottom.png")))), "MapElement"),
    PUSH_PANEL_135_LEFT_ELEMENT("/PushPanel135Left.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel135Left.png")))), "MapElement"),
    PUSH_PANEL_135_RIGHT_ELEMENT("/PushPanel135Right.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel135Right.png")))), "MapElement"),
    PUSH_PANEL_135_TOP_ELEMENT("/PushPanel135Top.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/PushPanel135Top.png")))), "MapElement"),
    REBOOT_ELEMENT("/Reboot.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Reboot.png")))), "MapElement"),
    STARTING_POINT_ELEMENT("/StartingPoint.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/StartingPoint.png")))), "MapElement"),
    WALL_BOTTOM_ELEMENT("/WallBottom.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/WallBottom.png")))), "MapElement"),
    WALL_LEFT_ELEMENT("/WallLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/WallLeft.png")))), "MapElement"),
    WALL_RIGHT_ELEMENT("/WallRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/WallRight.png")))), "MapElement"),
    WALL_TOP_ELEMENT("/WallTop.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/WallTop.png")))), "MapElement"),

    //racing courses
    DEATH_TRAP_MAP("/DeathTrapMap.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/DeathTrapMap.png")))), "Map"),
    DIZZY_HIGHWAY_MAP("/DizzyHighwayMap.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/DizzyHighwayMap.png")))), "Map"),
    EXTRA_CRISPY_MAP("/ExtraCrispyMap.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/ExtraCrispyMap.png")))), "Map"),
    LOST_BEARINGS_MAP("/LostBearingsMap.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LostBearingsMap.png")))), "Map"),

    //robots, BEV: bird eye view
    HAMMER_BOT_BEV("/RobotPink.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotPink.png")))), "Robot"),
    HULK_BOT_BEV("/RobotRed.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotRed.png")))), "Robot"),
    SMASH_BOT_BEV("/RobotYellow.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotYellow.png")))), "Robot"),
    SPIN_BOT_BEV("/RobotBlue.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotBlue.png")))), "Robot"),
    TWONKY_BOT_BEV("/RobotOrange.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotOrange.png")))), "Robot"),
    ZOOM_BOT_BEV("/RobotGreen.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotGreen.png")))), "Robot"),
    HAMMER_BOT("/RobotPinkSingle.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotPinkSingle.png")))), "Robot"),
    HULK_BOT("/RobotRedSingle.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotRedSingle.png")))), "Robot"),
    SMASH_BOT("/RobotYellowSingle.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotYellowSingle.png")))), "Robot"),
    SPIN_BOT("/RobotBlueSingle.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotBlueSingle.png")))), "Robot"),
    TWONKY_BOT("/RobotOrangeSingle.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotOrangeSingle.png")))), "Robot"),
    ZOOM_BOT("/RobotGreenSingle.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotGreenSingle.png")))), "Robot");


    public String string;
    public ImageView imageView;
    public String type;
    Images(String string, ImageView imageView, String type) {
        this.string = string;
        this.imageView = imageView;
        this.type = type;
    }

    public String toString(){
        return this.string;
    }
    public ImageView toAvailableCardsView(){
        return null;
    }

    public ImageView toImageView() {
        switch (this.type){
            case "MapElement" ->{
                return handleMapElement(new ImageView(new Image(this.toString())));
            }
            case "Map" ->{
                return handleMap(new ImageView(new Image(this.toString())));
            }
            default -> {
                return new ImageView(new Image(this.toString()));
            }

        }
    }
    public ImageView toImageView(Direction direction) {
        return handleRobot(imageView, direction);
    }
    public ImageView handleCard(ImageView imageView){
        imageView.setSmooth(true);
        imageView.setFitHeight(100);
        imageView.setFitWidth(65);
        return imageView;
    }
    public ImageView handleMapElement(ImageView imageView){
        imageView.setSmooth(true);
        imageView.setFitHeight(297);
        imageView.setFitWidth(297);
        return imageView;
    }

    public ImageView handleMap(ImageView imageView){
        imageView.setSmooth(true);
        // TODO set appropriate height & width - is this even needed @Sarp? (Vivian)
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }

    public ImageView handleRobot(ImageView imageView, Direction direction){
        switch (direction){

            case TOP -> {
                imageView.setRotate(270);

            }
            case BOTTOM -> {
                imageView.setRotate(90);
            }
            case RIGHT -> {
            }
            case LEFT -> {
                imageView.setRotate(180);
            }
        }
        imageView.setSmooth(true);
        imageView.setFitHeight(260);
        imageView.setFitWidth(297);
        return imageView;
    }

    //others
    //TODO check importance of, increase and add other images to the class

}
