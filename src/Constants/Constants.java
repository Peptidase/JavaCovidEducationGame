package Constants;

import java.util.ArrayList;
import java.util.List;

public interface Constants {
    int INITIAL_GAME_STATE = 1;

    int WIDTH = 600;
    int HEIGHT = 600;

     int ENEMY_HEIGHT = 40;
    int ENEMY_WIDTH = 40;
    int DELAY = 17;
    int PLAYERWIDTH = 15;
    int PLAYERHEIGHT = 10;

ArrayList<String> Questions = new ArrayList<String>(List.of(
        "Multiple vaccines can hurt a child"
        ,"You can protect others near you by getting a vaccine"
        ,"No diseases are eradicated and ever will be!"
        ,"Mild Illness is a reason to withold vaccination"
        ,"The Covid Vaccine was developed too quickly!"
        ,"A new needle should be used for different vaccinations"
        , "Have you enjoyed the quiz?"
        ,""));
ArrayList<String> Answers = new ArrayList<String>(List.of(
        "F"
        ,"T"
        ,"F"
        ,"F"
        ,"F"
        ,"T"
        ,"T"
        ,"T"));
ArrayList<String> Explainations = new ArrayList<String>(List.of(
        "False, Children need multiple vaccines to prevent them from being ill later in life"
        ,"True, If you have less chance of being sick you will have less chance of passing it onto others, reducing risk"
        ,"False, We have eradicated six different diseases completely(Smallpox being the most significant)"
        ,"False, A mild illness wont be cured via a vaccine, but it could help prevent worse pathogens from hurting your body"
        ,"False, The covid pandemic is dangerous and therfore more resources were spent on researching a cure faster"
        ,"True, Many other disease or illnesses could be present in the blood and are easily transmissible if the same syringe is used."
        ,"True, This game took me a long time to make, I hope you liked it :)"
        ,""));


}
