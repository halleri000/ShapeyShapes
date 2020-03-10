public class Gamestates{

    private static boolean MENU = true;
    private static boolean PAUSE = false;
    private static boolean PLAY = false;

    public static boolean isMENU(){
        return MENU;}

    public static void setMENU(boolean MENU){
        Gamestates.MENU = MENU;
    }

    public static boolean isPAUSE(){
        return PAUSE;}

    public static void setPAUSE(boolean PAUSE){
        Gamestates.PAUSE = PAUSE;
    }

    public static boolean isPLAY(){
        return PLAY;
    }

    public static void setPLAY(boolean PLAY){
        Gamestates.PLAY = PLAY;
    }
}


