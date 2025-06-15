package telran.photoSelector.tools;

public class PhotoSelector {
    // StringBuilder ??? (methods: append and toString().split);
    // TODO*: Pattern (compile, matcher) and Matcher (reset, find)
    public static String[] selectPictures(String[] pictures, String regex){
        int size = 0;
        for (int i = 0; i < pictures.length; i++) {
            if(pictures[i].matches(regex)){
                size++;
            }
        }
        String[] res = new String[size];
        for (int j = 0, k = 0; j < res.length; k++) {
            if (pictures[k].matches(regex)){
                res[j++] = pictures[k];
            }
        }
        return res;
    }
}
