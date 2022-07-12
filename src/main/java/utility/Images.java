package utility;

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
    ANTENNA_EAST_ELEMENT("/AntennaEast.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaEast.png")))), "MapElement"),
    ANTENNA_NORTH_ELEMENT("/AntennaNorth.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaNorth.png")))), "MapElement"),
    ANTENNA_SOUTH_ELEMENT("/AntennaSouth.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaSouth.png")))), "MapElement"),
    ANTENNA_WEST_ELEMENT("/AntennaWest.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/AntennaWest.png")))), "MapElement"),

    BELT_BLUE_CROSS_DOWN_ELEMENT("/BeltBlueCrossDown.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueCrossDown.png")))), "MapElement"),
    BELT_BLUE_DOWN_ELEMENT("/BeltBlueDown.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueDown.png")))), "MapElement"),
    BELT_BLUE_LEFT_ELEMENT("/BeltBlueLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueLeft.png")))), "MapElement"),
    BELT_BLUE_RIGHT_ELEMENT("/BeltBlueRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueRight.png")))), "MapElement"),
    BELT_BLUE_UP_ELEMENT("/BeltBlueUp.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueUp.png")))), "MapElement"),
    BELT_BLUE_UP_TO_LEFT_ELEMENT("/BeltBlueUpToLeft.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueUpToLeft.png")))), "MapElement"),
    BELT_BLUE_UP_TO_RIGHT_ELEMENT("/BeltBlueUpToRight.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltBlueUpToRight.png")))), "MapElement"),
    BELT_GREEN_DOWN_ELEMENT("/BeltGreenDown.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenDown.png")))), "MapElement"),
    BELT_GREEN_LEFT_ELEMENT("/BeltGreenLeft.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenLeft.png")))), "MapElement"),
    BELT_GREEN_RIGHT_ELEMENT("/BeltGreenRight.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenRight.png")))), "MapElement"),
    BELT_GREEN_UP_ELEMENT("/BeltGreenUp.png",new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenUp.png")))), "MapElement"),
    BELT_GREEN_UP_TO_LEFT_ELEMENT("/BeltGreenUpToLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenUpToLeft.png")))), "MapElement"),
    BELT_GREEN_UP_TO_RIGHT_ELEMENT("/BeltGreenUpToRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenUpToRight.png")))), "MapElement"),
    BELT_GREEN_ELEMENT("/BeltGreenUp.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/BeltGreenUp.png")))), "MapElement"),
    BELT_2X8_ELEMENT("/Board2x8.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/Board2x8.png")))), "MapElement"),
    CHECKPOINT_1_ELEMENT("/CheckPoint1.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint1.png")))), "MapElement"),
    CHECKPOINT_2_ELEMENT("/CheckPoint2.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint2.png")))), "MapElement"),
    CHECKPOINT_3_ELEMENT("/CheckPoint3.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint3.png")))), "MapElement"),
    CHECKPOINT_4_ELEMENT("/CheckPoint4.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/CheckPoint4.png")))), "MapElement"),
    ENERGY_SPACE_GREEN_ELEMENT("/EnergySpaceGreen.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/EnergySpaceGreen.png")))), "MapElement"),
    GEAR_CLOCKWISE_ELEMENT("/GearClockwise.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/GearClockwise.png")))), "MapElement"),
    GEAR_COUNTERCLOCKWISE_ELEMENT("/GearCounterclockwise.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/GearCounterclockwise.png")))), "MapElement"),
    LASER_DOWN_ELEMENT("/LaserDown.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserDown.png")))), "MapElement"),
    LASER_LEFT_ELEMENT("/LaserLeft.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserLeft.png")))), "MapElement"),
    LASER_RIGHT_ELEMENT("/LaserRight.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserRight.png")))), "MapElement"),
    LASER_UP_ELEMENT("/LaserUp.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/LaserUp.png")))), "MapElement"),
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
    DIZZY_HIGHWAY_MAP("/DizzyHighwayMap.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/DeathTrapMap.png")))), "Map"),
    EXTRA_CRISPY_MAP("/ExtraCrispyMap.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/DeathTrapMap.png")))), "Map"),
    LOST_BEARINGS_MAP("/LostBearingsMap.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/DeathTrapMap.png")))), "Map"),

    //robots
    HAMMER_BOT("/RobotPink.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotPink.png")))), "Robot"),
    HULK_BOT("/RobotRed.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotRed.png")))), "Robot"),
    SMASH_BOT("/RobotYellow.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotYellow.png")))), "Robot"),
    SPIN_BOT("/RobotBlue.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotBlue.png")))), "Robot"),
    TWONKY_BOT("/RobotOrange.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotOrange.png")))), "Robot"),
    ZOOM_BOT("/RobotGreen.png", new ImageView(new Image(Objects.requireNonNull(Images.class.getResourceAsStream("/RobotGreen.png")))), "Robot");


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

    public ImageView toImageView() {
        ImageView imageView = new ImageView(this.imageView.getImage());
        switch (this.type){
            case "Card" ->{
                return handleCard(imageView);
            }
            case "MapElement" ->{
                return handleMapElement(imageView);
            }
            case "Map" ->{
                return handleMap(imageView);
            }
            case "Robot" ->{
                return handleRobot(imageView);
            }

        }
        return this.imageView;
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

    public ImageView handleRobot(ImageView imageView){
        imageView.setSmooth(true);
        imageView.setFitHeight(260);
        imageView.setFitWidth(297);
        return imageView;
    }

    //others
    //TODO check importance of, increase and add other images to the class

}
