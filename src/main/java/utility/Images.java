package utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Erdogan
 * replaces image paths with shorter strings
 */
public enum Images {
    /*
    public void rotate(ImageView imageView, int direction){
        imageView.setRotate(imageView.getRotate()+direction*90);
    }
    //TODO edit*/

    //cards
    CARD_BACK_CARD("/CardBack.png", new ImageView(new Image("/CardBack.png")), "Card"),

    //programmingCards
    AGAIN_CARD("/Again.png", new ImageView(new Image("/Again.png")), "Card"),
    BACK_UP_CARD("/BackUp.png", new ImageView(new Image("/BackUp.png")), "Card"),
    MOVE_1_CARD("/Move1.png", new ImageView(new Image("/Move1.png")), "Card"),
    MOVE_2_CARD("/Move2.png", new ImageView(new Image("/Move2.png")), "Card"),
    MOVE_3_CARD("/Move3.png", new ImageView(new Image("/Move3.png")), "Card"),
    POWER_UP_CARD("PowerUp.png", new ImageView(new Image("/PowerUp.png")), "Card"),
    TURN_LEFT_CARD("TurnLeft.png", new ImageView(new Image("/TurnLeft.png")), "Card"),
    TURN_RIGHT_CARD("/TurnRight.png", new ImageView(new Image("/TurnRight.png")), "Card"),
    U_TURN_CARD("/UTurn.png", new ImageView(new Image("/UTurn.png")), "Card"),

    //damageCards
    SPAM_CARD("/Spam.png", new ImageView(new Image("/Spam.png")), "Card"),
    TROJAN_HORSE_CARD("/TrojanHorse.png", new ImageView(new Image("/TrojanHorse.png")), "Card"),
    VIRUS_CARD("/Virus.png", new ImageView(new Image("/Virus.png")), "Card"),
    WORM_CARD("/Worm.png", new ImageView(new Image("/Worm.png")), "Card"),

    //specialProgrammingCards
    ENERGY_ROUTINE_CARD("/EnergyRoutine.png", new ImageView(new Image("/EnergyRoutine.png")), "Card"),
    REPEAT_ROUTINE_CARD("/RepeatRoutine.png", new ImageView(new Image("/RepeatRoutine.png")), "Card"),
    SANDBOX_ROUTINE_CARD("/SandboxRoutine.png", new ImageView(new Image("/SandboxRoutine.png")), "Card"),
    SPAM_FOLDER_CARD("/SpamFolder.png", new ImageView(new Image("/SpamFolder.png")), "Card"),
    SPEED_ROUTINE_CARD("/SpeedRoutine.png", new ImageView(new Image("/SpeedRoutine.png")), "Card"),
    WEASEL_ROUTINE_CARD("/WeaselRoutine.png", new ImageView(new Image("/WeaselRoutine.png")), "Card"),

    //board elements
    ANTENNA_EAST_ELEMENT("/AntennaEast.png", new ImageView(new Image("/AntennaEast.png")), "MapElement"),
    ANTENNA_NORTH_ELEMENT("/AntennaNorth.png", new ImageView(new Image("/AntennaNorth.png")), "MapElement"),
    ANTENNA_SOUTH_ELEMENT("/AntennaSouth.png", new ImageView(new Image("/AntennaSouth.png")), "MapElement"),
    ANTENNA_WEST_ELEMENT("/AntennaWest.png", new ImageView(new Image("/AntennaWest.png")), "MapElement"),

    BELT_BLUE_CROSS_DOWN_ELEMENT("/BeltBlueCrossDown.png", new ImageView(new Image("/BeltBlueCrossDown.png")), "MapElement"),
    BELT_BLUE_DOWN_ELEMENT("/BeltBlueDown.png", new ImageView(new Image("/BeltBlueDown.png")), "MapElement"),
    BELT_BLUE_LEFT_ELEMENT("/BeltBlueLeft.png", new ImageView(new Image("/BeltBlueLeft.png")), "MapElement"),
    BELT_BLUE_RIGHT_ELEMENT("/BeltBlueRight.png", new ImageView(new Image("/BeltBlueRight.png")), "MapElement"),
    BELT_BLUE_UP_ELEMENT("/BeltBlueUp.png",new ImageView(new Image("/BeltBlueUp.png")), "MapElement"),
    BELT_BLUE_UP_TO_LEFT_ELEMENT("/BeltBlueUpToLeft.png",new ImageView(new Image("/BeltBlueUpToLeft.png")), "MapElement"),
    BELT_BLUE_UP_TO_RIGHT_ELEMENT("/BeltBlueUpToRight.png",new ImageView(new Image("/BeltBlueUpToRight.png")), "MapElement"),
    BELT_GREEN_DOWN_ELEMENT("/BeltGreenDown.png",new ImageView(new Image("/BeltGreenDown.png")), "MapElement"),
    BELT_GREEN_LEFT_ELEMENT("/BeltGreenLeft.png",new ImageView(new Image("/BeltGreenLeft.png")), "MapElement"),
    BELT_GREEN_RIGHT_ELEMENT("/BeltGreenRight.png",new ImageView(new Image("/BeltGreenRight.png")), "MapElement"),
    BELT_GREEN_UP_ELEMENT("/BeltGreenUp.png",new ImageView(new Image("/BeltGreenUp.png")), "MapElement"),
    BELT_GREEN_UP_TO_LEFT_ELEMENT("/BeltGreenUpToLeft.png", new ImageView(new Image("/BeltGreenUpToLeft.png")), "MapElement"),
    BELT_GREEN_UP_TO_RIGHT_ELEMENT("/BeltGreenUpToRight.png", new ImageView(new Image("/BeltGreenUpToRight.png")), "MapElement"),
    BELT_GREEN_ELEMENT("/BeltGreenUp.png", new ImageView(new Image("/BeltGreenUp.png")), "MapElement"),
    BELT_2X8_ELEMENT("/Board2x8.png", new ImageView(new Image("/Board2x8.png")), "MapElement"),
    CHECKPOINT_1_ELEMENT("/CheckPoint1.png", new ImageView(new Image("/CheckPoint1.png")), "MapElement"),
    CHECKPOINT_2_ELEMENT("/CheckPoint2.png", new ImageView(new Image("/CheckPoint2.png")), "MapElement"),
    CHECKPOINT_3_ELEMENT("/CheckPoint3.png", new ImageView(new Image("/CheckPoint3.png")), "MapElement"),
    CHECKPOINT_4_ELEMENT("/CheckPoint4.png", new ImageView(new Image("/CheckPoint4.png")), "MapElement"),
    ENERGY_SPACE_GREEN_ELEMENT("/EnergySpaceGreen.png", new ImageView(new Image("/EnergySpaceGreen.png")), "MapElement"),
    GEAR_CLOCKWISE_ELEMENT("/GearClockwise.png", new ImageView(new Image("/GearClockwise.png")), "MapElement"),
    GEAR_COUNTERCLOCKWISE_ELEMENT("/GearCounterclockwise.png", new ImageView(new Image("/GearCounterclockwise.png")), "MapElement"),
    LASER_DOWN_ELEMENT("/LaserDown.png", new ImageView(new Image("/LaserDown.png")), "MapElement"),
    LASER_LEFT_ELEMENT("/LaserLeft.png", new ImageView(new Image("/LaserLeft.png")), "MapElement"),
    LASER_RIGHT_ELEMENT("/LaserRight.png", new ImageView(new Image("/LaserRight.png")), "MapElement"),
    LASER_UP_ELEMENT("/LaserUp.png", new ImageView(new Image("/LaserUp.png")), "MapElement"),
    PIT_ELEMENT("/Pit.png", new ImageView(new Image("/Pit.png")), "MapElement"),
    PUSH_PANEL_24_BOTTOM_ELEMENT("/PushPanel24Bottom.png", new ImageView(new Image("/PushPanel24Bottom.png")), "MapElement"),
    PUSH_PANEL_24_LEFT_ELEMENT("/PushPanel24Left.png", new ImageView(new Image("/PushPanel24Left.png")), "MapElement"),
    PUSH_PANEL_24_RIGHT_ELEMENT("/PushPanel24Right.png", new ImageView(new Image("/PushPanel24Right.png")), "MapElement"),
    PUSH_PANEL_24_TOP_ELEMENT("/PushPanel24Top.png", new ImageView(new Image("/PushPanel24Top.png")), "MapElement"),
    PUSH_PANEL_135_BOTTOM_ELEMENT("/PushPanel135Bottom.png", new ImageView(new Image("/PushPanel135Bottom.png")), "MapElement"),
    PUSH_PANEL_135_LEFT_ELEMENT("/PushPanel135Left.png", new ImageView(new Image("/PushPanel135Left.png")), "MapElement"),
    PUSH_PANEL_135_RIGHT_ELEMENT("/PushPanel135Right.png", new ImageView(new Image("/PushPanel135Right.png")), "MapElement"),
    PUSH_PANEL_135_TOP_ELEMENT("/PushPanel135Top.png", new ImageView(new Image("/PushPanel135Top.png")), "MapElement"),
    REBOOT_ELEMENT("/Reboot.png", new ImageView(new Image("/Reboot.png")), "MapElement"),
    STARTING_POINT_ELEMENT("/StartingPoint.png", new ImageView(new Image("/StartingPoint.png")), "MapElement"),
    WALL_BOTTOM_ELEMENT("/WallBottom.png", new ImageView(new Image("/WallBottom.png")), "MapElement"),
    WALL_LEFT_ELEMENT("/WallLeft.png", new ImageView(new Image("/WallLeft.png")), "MapElement"),
    WALL_RIGHT_ELEMENT("/WallRight.png", new ImageView(new Image("/WallRight.png")), "MapElement"),
    WALL_TOP_ELEMENT("/WallTop.png", new ImageView(new Image("/WallTop.png")), "MapElement"),

    //racing courses
    DEATH_TRAP_MAP("/DeathTrapMap.png", new ImageView(new Image("/DeathTrapMap.png")), "Map"),
    DIZZY_HIGHWAY_MAP("/DeathTrapMap.png", new ImageView(new Image("/DeathTrapMap.png")), "Map"),
    EXTRA_CRISPY_MAP("/DeathTrapMap.png", new ImageView(new Image("/DeathTrapMap.png")), "Map"),
    LOST_BEARINGS_MAP("/DeathTrapMap.png", new ImageView(new Image("/DeathTrapMap.png")), "Map"),

    //robots
    HAMMER_BOT("/RobotPurpleHammer.png", new ImageView(new Image("/RobotPurpleHammer.png")), "Robot"),
    HULK_BOT("/RobotRedHulkx90.png", new ImageView(new Image("/RobotRedHulkx90.png")), "Robot"),
    SMASH_BOT("/RobotYellowSmash.png", new ImageView(new Image("/RobotYellowSmash.png")), "Robot"),
    SPIN_BOT("/RobotBlueSpin.png", new ImageView(new Image("/RobotBlueSpin.png")), "Robot"),
    TWONKY_BOT("/RobotOrangeTwonky.png", new ImageView(new Image("/RobotOrangeTwonky.png")), "Robot"),
    ZOOM_BOT("/RobotGreenZoom.png", new ImageView(new Image("/RobotGreenZoom.png")), "Robot");


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
        imageView.setFitHeight(200);
        imageView.setFitWidth(130);
        return imageView;
    }
    public ImageView handleMapElement(ImageView imageView){
        imageView.setSmooth(true);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
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
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        return imageView;
    }

    //others
    //TODO check importance of, increase and add other images to the class

}
