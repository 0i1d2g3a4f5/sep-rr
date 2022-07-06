package utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Isabel
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
    AGAINCARD("images/cards/programmingCards/Again.png", new ImageView(new Image("images/programmingCards/Again.png"))),
    TURN_RIGHT_CARD("images/cards/programmingCards/TurnRight.png", new ImageView(new Image("images/cards/programmingCards/TurnRight.png"))),
    POWER_UP_CARD("images/cards/programmingCards/PowerUp.png", new ImageView(new Image("images/cards/programmingCards/PowerUp.png"))),
    MOVE_1_CARD("images/cards/programmingCards/Move1.png", new ImageView(new Image("images/cards/programmingCards/Move1.png"))),
    MOVE_2_CARD("images/cards/programmingCards/Move2.png", new ImageView(new Image("images/cards/programmingCards/Move2.png"))),
    MOVE_3_CARD("images/cards/programmingCards/Move3.png", new ImageView(new Image("images/cards/programmingCards/Move3.png"))),
    TURN_LEFT_CARD("images/cards/programmingCards/TurnLeft.png", new ImageView(new Image("images/cards/programmingCards/TurnLeft.png"))),
    U_TURN_CARD("images/cards/programmingCards/UTurn.png", new ImageView(new Image("images/cards/programmingCards/UTurn.png"))),
    //damageCards
    SPAM_CARD("images/cards/damageCards/Spam.png", new ImageView(new Image("images/cards/damageCards/Spam.png"))),
    TROJAN_HORSE_CARD("images/cards/damageCards/TrojanHorse.png", new ImageView(new Image("images/cards/damageCards/TrojanHorse.png"))),
    VIRUS_CARD("images/cards/damageCards/Virus.png", new ImageView(new Image("images/cards/damageCards/Virus.png"))),
    WORM_CARD("images/cards/damageCards/Worm.png", new ImageView(new Image("images/cards/damageCards/Worm.png"))),
    //specialProgrammingCards

    ENERGY_ROUTINE_CARD("images/cards/specialProgrammingCards/EnergyRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/EnergyRoutine.png"))),
    REPEAT_ROUTINE_CARD("images/cards/specialProgrammingCards/RepeatRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/RepeatRoutine.png"))),
    SANDBOX_ROUTINE_CARD("images/cards/specialProgrammingCards/SandboxRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/SandboxRoutine.png"))),
    SPAM_FOLDER_CARD("images/cards/specialProgrammingCards/SpamFolder.png", new ImageView(new Image("images/cards/specialProgrammingCards/SpamFolder.png"))),
    SPEED_ROUTINE_CARD("images/cards/specialProgrammingCards/SpeedRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/SpeedRoutine.png"))),
    WEASEL_ROUTINE_CARD("images/cards/specialProgrammingCards/WeaselRoutine.png", new ImageView(new Image("images/cards/specialProgrammingCards/WeaselRoutine.png"))),

    //board elements
    ANTENNA_EAST("images/boardElements/AntennaEast.png", new ImageView(new Image("images/boardElements/AntennaEast.png"))),
    ANTENNA_NORTH_ELEMENTS("images/boardElements/AntennaNorth.png", new ImageView(new Image("images/boardElements/AntennaNorth.png"))),
    ANTENNA_SOUTH_ELEMENT("images/boardElements/AntennaSouth.png", new ImageView(new Image("images/boardElements/AntennaSouth.png"))),
    ANTENNA_WEST_ELEMENT("images/boardElements/AntennaWest.png", new ImageView(new Image("images/boardElements/AntennaWest.png"))),
    BELT_BLUE_CROSS_ELEMENT("images/boardElements/BeltBlueCrossDown.png", new ImageView(new Image("images/boardElements/BeltBlueCrossDown.png"))),
    BELT_BLUE_CURVE_ELEMENT("images/boardElements/BeltBlueUpToLeft.png",new ImageView(new Image("images/boardElements/BeltBlueUpToLeft.png"))),
    BELT_BLUE_ELEMENT("images/boardElements/BeltBlueUp.png", new ImageView(new Image("images/boardElements/BeltBlueUp.png"))),
    BELT_GREEN_CURVE_ELEMENT("images/boardElements/BeltGreenUpToLeft.png", new ImageView(new Image("images/boardElements/BeltGreenUpToLeft.png"))),
    BELT_GREEN_ELEMENT("images/boardElements/BeltGreenUp.png", new ImageView(new Image("images/boardElements/BeltGreenUp.png"))),
    BELT_2X8_ELEMENT("images/boardElements/Board2x8.png", new ImageView(new Image("images/boardElements/Board2x8.png"))),
    CHECKPOINT_1_ELEMENT("images/boardElements/CheckPoint1.png", new ImageView(new Image("images/boardElements/CheckPoint1.png"))),
    CHECKPOINT_2_ELEMENT("images/boardElements/CheckPoint2.png", new ImageView(new Image("images/boardElements/CheckPoint2.png"))),
    CHECKPOINT_3_ELEMENT("images/boardElements/CheckPoint3.png", new ImageView(new Image("images/boardElements/CheckPoint3.png"))),
    CHECKPOINT_4_ELEMENT("images/boardElements/CheckPoint4.png", new ImageView(new Image("images/boardElements/CheckPoint4.png"))),
    ENERGY_SPACE_GREEN_ELEMENT("images/boardElements/EnergySpaceGreen.png", new ImageView(new Image("images/boardElements/EnergySpaceGreen.png"))),
    GEAR_CLOCKWISE_ELEMENT("images/boardElements/GearClockwise.png", new ImageView(new Image("images/boardElements/GearClockwise.png"))),
    GEAR_COUNTERCLOCKWISE_ELEMENT("images/boardElements/GearCounterclockwise.png", new ImageView(new Image("images/boardElements/GearCounterclockwise.png"))),
    LASER_1_ELEMENT("images/boardElements/LaserUp.png", new ImageView(new Image("images/boardElements/LaserUp.png"))),
    PIT_ELEMENT("images/boardElements/Pit.png", new ImageView(new Image("images/boardElements/Pit.png"))),
    PUSH_PANEL_24_TOP_ELEMENT("images/boardElements/PushPanel24Top.png", new ImageView(new Image("images/boardElements/PushPanel24Top.png"))),
    PUSH_PANEL_24_BOTTOM_ELEMENT("images/boardElements/PushPanel24Bottom.png", new ImageView(new Image("images/boardElements/PushPanel24Bottom.png"))),
    PUSH_PANEL_24_RIGHT_ELEMENT("images/boardElements/PushPanel24Right.png", new ImageView(new Image("images/boardElements/PushPanel24Right.png"))),
    PUSH_PANEL_24_LEFT_ELEMENT("images/boardElements/PushPanel24Left.png", new ImageView(new Image("images/boardElements/PushPanel24Left.png"))),
    PUSH_PANEL_135_TOP_ELEMENT("images/boardElements/PushPanel135Top.png", new ImageView(new Image("images/boardElements/PushPanel135Top.png"))),
    PUSH_PANEL_135_BOTTOM_ELEMENT("images/boardElements/PushPanel135Bottom.png", new ImageView(new Image("images/boardElements/PushPanel135Bottom.png"))),
    PUSH_PANEL_135_RIGHT_ELEMENT("images/boardElements/PushPanel135Right.png", new ImageView(new Image("images/boardElements/PushPanel135Right.png"))),
    PUSH_PANEL_135_LEFT_ELEMENT("images/boardElements/PushPanel135Left.png", new ImageView(new Image("images/boardElements/PushPanel135Left.png"))),


    REBOOT_ELEMENT("images/boardElements/Reboot.png", new ImageView(new Image("images/boardElements/Reboot.png"))),
    STARTING_POINT_ELEMENT("images/boardElements/StartingPoint.png", new ImageView(new Image("images/boardElements/StartingPoint.png"))),
    WALL_TOP_ELEMENT("images/boardElements/WallTop.png", new ImageView(new Image("images/boardElements/WallTop.png"))),
    WALL_BOTTOM_ELEMENT("images/boardElements/WallBottom.png", new ImageView(new Image("images/boardElements/WallBottom.png"))),
    WALL_RIGHT_ELEMENT("images/boardElements/WallRight.png", new ImageView(new Image("images/boardElements/WallRight.png"))),
    WALL_LEFT_ELEMENT("images/boardElements/WallLeft.png", new ImageView(new Image("images/boardElements/WallLeft.png"))),

    //racing courses
    DEATH_TRAP_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png"))),
    DIZZY_HIGHWAY_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png"))),
    EXTRA_CRISPY_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png"))),
    LOST_BEARINGS_MAP("images/racingCourses/DeathTrapMap.png", new ImageView(new Image("images/racingCourses/DeathTrapMap.png"))),

    //robots
    HAMMER_BOT("images/robots/RobotPurpleHammer.png", new ImageView(new Image("images/robots/RobotPurpleHammer.png"))),
    HULK_BOT("images/robots/RobotRedHulkx90.png", new ImageView(new Image("images/robots/RobotRedHulkx90.png"))),
    SMASH_BOT("images/robots/RobotYellowSmash.png", new ImageView(new Image("images/robots/RobotYellowSmash.png"))),
    SPIN_BOT("images/robots/RobotBlueSpin.png", new ImageView(new Image("images/robots/RobotBlueSpin.png"))),
    TWONKY_BOT("images/robots/RobotOrangeTwonky.png", new ImageView(new Image("images/robots/RobotOrangeTwonky.png"))),
    ZOOM_BOT("images/robots/RobotGreenZoom.png", new ImageView(new Image("images/robots/RobotGreenZoom.png")));

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
                return handleCard(this.toImageView());
            }
            case "MapElement" ->{
                return handleMapElement(this.toImageView());
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



    //others
    //TODO check importance of, increase and add other images to the class

}
