package telran.photoSelector.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhotoSelector {

    public static String[] selectPictures(String[] pictures, String regex){
        StringBuilder sb = new StringBuilder();
        for(String picture : pictures){
            if (picture.matches(regex))
                sb.append(picture);
        }
        return sb.toString().split("(?<=.jpg|.png|.gif)");
    }


    public static String[] selectPictures2(String[] pictures, String regex){
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        for(String picture : pictures) {
            matcher = pattern.matcher(picture);
            if (matcher.find())
                sb.append(picture);
        }

        return sb.toString().split("(?<=.jpg|.png|.gif)");
    }


    public static String[] selectPictures3(String[] pictures, String regex){
        List<String> filteredPictures = new ArrayList<>();

        for(String picture : pictures) {
            if(picture.matches(regex))
                filteredPictures.add(picture);
        }

        return filteredPictures.toArray(new String[0]);
    }
}
