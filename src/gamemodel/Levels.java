package gamemodel;

/**
 * Created by C12508463 on 18/11/2016.
 */
public enum Levels {
    LEVEL_ONE(5,1),
    LEVEL_TWO(10,2),
    LEVEL_THREE(15,4),
    LEVEL_FOUR(20,6),
    LEVEL_FIVE(25,10),
    LEVEL_SIX(30,15),
    LEVEL_SEVEN(35,18),
    LEVEL_EIGHT(40,22),
    LEVEL_NINE(45,30),
    LEVEL_TEN(50,36),
    LEVEL_ELEVEN(55,48),
    LEVEL_TWELVE(60,54);

    private final int balls;
    private final int ballsToPass;

    Levels(int balls, int ballsToPass){
        this.balls = balls;
        this.ballsToPass = ballsToPass;
    }

    public int getBalls(){
        return balls;
    }
    public int getBallsToPass(){
        return ballsToPass;
    }



}
