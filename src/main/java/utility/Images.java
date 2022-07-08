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
    CARD_BACK_CARD ("images/cards/CardBack.png", new ImageView(new Image("images/cards/CardBack.png")), "Card"),

    //programmingCards
    AGAIN_CARD("images/cards/programmingCards/Again.png", new ImageView(new Image("images/programmingCards/Again.png")), "Card"),
    BACK_UP_CARD("images/cards/programmingCards/BackUp.png", new ImageView(new Image("images/programmingCards/BackUp.png")), "Card"),
    MOVE_1_CARD("images/cards/programmingCards/Move1.png", new ImageView(new Image("images/programmingCards/Move1.png")), "Card"),
    MOVE_2_CARD("images/cards/programmingCards/Move2.png", new ImageView(new Image("images/programmingCards/Move2.png")), "Card"),
    MOVE_3_CARD("images/cards/programmingCards/Move3.png", new ImageView(new Image("images/programmingCards/Move3.png")), "Card"),
    POWER_UP_CARD("images/cards/programmingCards/PowerUp.png", new ImageView(new Image("images/programmingCards/PowerUp.png")), "Card"),
    TURN_LEFT_CARD("images/cards/programmingCards/TurnLeft.png", new ImageView(new Image("images/programmingCards/TurnLeft.png")), "Card"),
    TURN_RIGHT_CARD("images/cards/programmingCards/TurnRight.png", new ImageView(new Image("images/cards/programmingCards/TurnRight.png")), "Card"),
    U_TURN_CARD("images/cards/programmingCards/UTurn.png", new ImageView(new Image("images/cards/programmingCards/UTurn.png")), "Card"),

    //damageCards
    SPAM_CARD("images/cards/damageCards/Spam.png", new ImageView(new Image("images/cards/damageCards/Spam.png")), "Card"),
    TROJAN_HORSE_CARD("images/cards/damageCards/TrojanHorse.png", new ImageView(new Image("images/cards/damageCards/TrojanHorse.png")), "Card"),
    VIRUS_CARD("images/cards/damageCards/Virus.png", new ImageView(new Image("images/cards/damageCards/Virus.png")), "Card"),
    WORM_CARD("images/cards/damageCards/Worm.png", new ImageView(new Image("images/cards/damageCards/Worm.png")), "Card"),

    //specialProgrammingCards
    ENERGY_ROUTINE_CARD("images/cards/specialProgrammingCards/EnergyRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/EnergyRoutine.png")), "Card"),
    REPEAT_ROUTINE_CARD("images/cards/specialProgrammingCards/RepeatRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/RepeatRoutine.png")), "Card"),
    SANDBOX_ROUTINE_CARD("images/cards/specialProgrammingCards/SandboxRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/SandboxRoutine.png")), "Card"),
    SPAM_FOLDER_CARD("images/cards/specialProgrammingCards/SpamFolder.png", new ImageView(new Image("images/cards/specialProgrammingCards/SpamFolder.png")), "Card"),
    SPEED_ROUTINE_CARD("images/cards/specialProgrammingCards/SpeedRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/SpeedRoutine.png")), "Card"),
    WEASEL_ROUTINE_CARD("images/cards/specialProgrammingCards/WeaselRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/WeaselRoutine.png")), "Card"),

    //board elements
    ANTENNA_EAST_ELEMENT("images/boardElements/AntennaEast.png", new ImageView(new Image("images/boardElements/AntennaEast.png")), "MapElement"),
    ANTENNA_NORTH_ELEMENT("images/boardElements/AntennaNorth.png", new ImageView(new Image("images/boardElements/AntennaNorth.png")), "MapElement"),
    ANTENNA_SOUTH_ELEMENT("images/boardElements/AntennaSouth.png", new ImageView(new Image("images/boardElements/AntennaSouth.png")), "MapElement"),
    ANTENNA_WEST_ELEMENT("images/boardElements/AntennaWest.png", new ImageView(new Image("images/boardElements/AntennaWest.png")), "MapElement"),
    BELT_BLUE_CROSS_DOWN_ELEMENT("images/boardElements/BeltBlueCrossDown.png", new ImageView(new Image("images/boardElements/BeltBlueCrossDown.png")), "MapElement"),
    BELT_BLUE_DOWN_ELEMENT("images/boardElements/BeltBlueDown.png", new ImageView(new Image("images/boardElements/BeltBlueDown.png")), "MapElement"),
    BELT_BLUE_LEFT_ELEMENT("images/boardElements/BeltBlueLeft.png", new ImageView(new Image("images/boardElements/BeltBlueLeft.png")), "MapElement"),
    BELT_BLUE_RIGHT_ELEMENT("images/boardElements/BeltBlueRight.png", new ImageView(new Image("images/boardElements/BeltBlueRight.png")), "MapElement"),
    BELT_BLUE_UP_ELEMENT("images/boardElements/BeltBlueUp.png",new ImageView(new Image("images/boardElements/BeltBlueUp.png")), "MapElement"),
    BELT_BLUE_UP_TO_LEFT_ELEMENT("images/boardElements/BeltBlueUpToLeft.png",new ImageView(new Image("images/boardElements/BeltBlueUpToLeft.png")), "MapElement"),
    BELT_BLUE_UP_TO_RIGHT_ELEMENT("images/boardElements/BeltBlueUpToRight.png",new ImageView(new Image("images/boardElements/BeltBlueUpToRight.png")), "MapElement"),
    BELT_GREEN_DOWN_ELEMENT("images/boardElements/BeltGreenDown.png",new ImageView(new Image("images/boardElements/BeltGreenDown.png")), "MapElement"),
    BELT_GREEN_LEFT_ELEMENT("images/boardElements/BeltGreenLeft.png",new ImageView(new Image("images/boardElements/BeltGreenLeft.png")), "MapElement"),
    BELT_GREEN_RIGHT_ELEMENT("images/boardElements/BeltGreenRight.png",new ImageView(new Image("images/boardElements/BeltGreenRight.png")), "MapElement"),
    BELT_GREEN_UP_ELEMENT("images/boardElements/BeltGreenUp.png",new ImageView(new Image("images/boardElements/BeltGreenUp.png")), "MapElement"),
    BELT_GREEN_UP_TO_LEFT_ELEMENT("images/boardElements/BeltGreenUpToLeft.png", new ImageView(new Image("images/boardElements/BeltGreenUpToLeft.png")), "MapElement"),
    BELT_GREEN_UP_TO_RIGHT_ELEMENT("images/boardElements/BeltGreenUpToRight.png", new ImageView(new Image("images/boardElements/BeltGreenUpToRight.png")), "MapElement"),
    BELT_GREEN_ELEMENT("images/boardElements/BeltGreenUp.png", new ImageView(new Image("images/boardElements/BeltGreenUp.png")), "MapElement"),
    BELT_2X8_ELEMENT("images/boardElements/Board2x8.png", new ImageView(new Image("images/boardElements/Board2x8.png")), "MapElement"),
    CHECKPOINT_1_ELEMENT("images/boardElements/CheckPoint1.png", new ImageView(new Image("images/boardElements/CheckPoint1.png")), "MapElement"),
    CHECKPOINT_2_ELEMENT("images/boardElements/CheckPoint2.png", new ImageView(new Image("images/boardElements/CheckPoint2.png")), "MapElement"),
    CHECKPOINT_3_ELEMENT("images/boardElements/CheckPoint3.png", new ImageView(new Image("images/boardElements/CheckPoint3.png")), "MapElement"),
    CHECKPOINT_4_ELEMENT("images/boardElements/CheckPoint4.png", new ImageView(new Image("images/boardElements/CheckPoint4.png")), "MapElement"),
    ENERGY_SPACE_GREEN_ELEMENT("images/boardElements/EnergySpaceGreen.png", new ImageView(new Image("images/boardElements/EnergySpaceGreen.png")), "MapElement"),
    GEAR_CLOCKWISE_ELEMENT("images/boardElements/GearClockwise.png", new ImageView(new Image("images/boardElements/GearClockwise.png")), "MapElement"),
    GEAR_COUNTERCLOCKWISE_ELEMENT("images/boardElements/GearCounterclockwise.png", new ImageView(new Image("images/boardElements/GearCounterclockwise.png")), "MapElement"),
    LASER_DOWN_ELEMENT("images/boardElements/LaserDown.png", new ImageView(new Image("images/boardElements/LaserDown.png")), "MapElement"),
    LASER_LEFT_ELEMENT("images/boardElements/LaserLeft.png", new ImageView(new Image("images/boardElements/LaserLeft.png")), "MapElement"),
    LASER_RIGHT_ELEMENT("images/boardElements/LaserRight.png", new ImageView(new Image("images/boardElements/LaserRight.png")), "MapElement"),
    LASER_UP_ELEMENT("images/boardElements/LaserUp.png", new ImageView(new Image("images/boardElements/LaserUp.png")), "MapElement"),
    PIT_ELEMENT("images/boardElements/Pit.png", new ImageView(new Image("images/boardElements/Pit.png")), "MapElement"),
    PUSH_PANEL_24_BOTTOM_ELEMENT("images/boardElements/PushPanel24Bottom.png", new ImageView(new Image("images/boardElements/PushPanel24Bottom.png")), "MapElement"),
    PUSH_PANEL_24_LEFT_ELEMENT("images/boardElements/PushPanel24Left.png", new ImageView(new Image("images/boardElements/PushPanel24Left.png")), "MapElement"),
    PUSH_PANEL_24_RIGHT_ELEMENT("images/boardElements/PushPanel24Right.png", new ImageView(new Image("images/boardElements/PushPanel24Right.png")), "MapElement"),
    PUSH_PANEL_24_TOP_ELEMENT("images/boardElements/PushPanel24Top.png", new ImageView(new Image("images/boardElements/PushPanel24Top.png")), "MapElement"),
    PUSH_PANEL_135_BOTTOM_ELEMENT("images/boardElements/PushPanel135Bottom.png", new ImageView(new Image("images/boardElements/PushPanel135Bottom.png")), "MapElement"),
    PUSH_PANEL_135_LEFT_ELEMENT("images/boardElements/PushPanel135Left.png", new ImageView(new Image("images/boardElements/PushPanel135Left.png")), "MapElement"),
    PUSH_PANEL_135_RIGHT_ELEMENT("images/boardElements/PushPanel135Right.png", new ImageView(new Image("images/boardElements/PushPanel135Right.png")), "MapElement"),
    PUSH_PANEL_135_TOP_ELEMENT("images/boardElements/PushPanel135Top.png", new ImageView(new Image("images/boardElements/PushPanel135Top.png")), "MapElement"),
    REBOOT_ELEMENT("images/boardElements/Reboot.png", new ImageView(new Image("images/boardElements/Reboot.png")), "MapElement"),
    STARTING_POINT_ELEMENT("images/boardElements/StartingPoint.png", new ImageView(new Image("images/boardElements/StartingPoint.png")), "MapElement"),
    WALL_BOTTOM_ELEMENT("images/boardElements/WallBottom.png", new ImageView(new Image("images/boardElements/WallBottom.png")), "MapElement"),
    WALL_LEFT_ELEMENT("images/boardElements/WallLeft.png", new ImageView(new Image("images/boardElements/WallLeft.png")), "MapElement"),
    WALL_RIGHT_ELEMENT("images/boardElements/WallRight.png", new ImageView(new Image("images/boardElements/WallRight.png")), "MapElement"),
    WALL_TOP_ELEMENT("images/boardElements/WallTop.png", new ImageView(new Image("images/boardElements/WallTop.png")), "MapElement"),

    //racing courses
    DEATH_TRAP_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png")), "Map"),
    DIZZY_HIGHWAY_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png")), "Map"),
    EXTRA_CRISPY_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png")), "Map"),
    LOST_BEARINGS_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png")), "Map"),

    //robots
    HAMMER_BOT("images/robots/RobotPurpleHammer.png", new ImageView(new Image("images/robots/RobotPurpleHammer.png")), "Robot"),
    HULK_BOT("images/robots/RobotRedHulkx90.png", new ImageView(new Image("images/robots/RobotRedHulkx90.png")), "Robot"),
    SMASH_BOT("images/robots/RobotYellowSmash.png", new ImageView(new Image("images/robots/RobotYellowSmash.png")), "Robot"),
    SPIN_BOT("images/robots/RobotBlueSpin.png", new ImageView(new Image("images/robots/RobotBlueSpin.png")), "Robot"),
    TWONKY_BOT("images/robots/RobotOrangeTwonky.png", new ImageView(new Image("images/robots/RobotOrangeTwonky.png")), "Robot"),
    ZOOM_BOT("images/robots/RobotGreenZoom.png", new ImageView(new Image("images/robots/RobotGreenZoom.png")), "Robot");

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
        imageView.setFitWidth(50);
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
        //TODO set appropriate height & width - is this even needed? (Vivian)
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
