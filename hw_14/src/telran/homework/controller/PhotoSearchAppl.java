package telran.homework.controller;

public class PhotoSearchAppl {

    static final String[] PHOTO_NAMES = {
            "Paris\\20140101_090000.jpg",
            "Paris\\20140201_121005.jpg",
            "Paris\\20150301_211035.jpg",
            "Paris\\20150401_110023.gif",
            "Paris\\20150401_181705.jpg",
            "Paris\\20150501_231035.gif",
            "London\\20140205_090000.jpg",
            "London\\20140205_121005.jpg",
            "London\\20140601_211035.gif",
            "London\\20151001_110023.jpg",
            "London\\20151001_121705.jpg",
            "London\\20151001_231035.jpg",
            "Chicago\\20150301_120001.png",
            "Chicago\\20151111_232000.png"
    };
    public static void main(String[] args) {
        System.out.println("=====EUROPEAN_PICTURES=====");
        String regexEuropean = "(Paris|London).*";
        findPhotos(PHOTO_NAMES, regexEuropean);

        System.out.println("=====AUTUMN_PICTURES=====");
        String regexAutumn = ".*\\\\\\d{4}(09|10|11).*";
        findPhotos(PHOTO_NAMES, regexAutumn);

        System.out.println("=====SPRING_2015_PICTURES=====");
        String regex2015Spring = ".*\\\\2015(03|04|05).*";
        findPhotos(PHOTO_NAMES, regex2015Spring);

        System.out.println("=====NIGHT_18:00_TO_24:00_PICTURES=====");
        String regexNight18to24 = ".*\\\\\\d{8}_(1[8|9]|2[0-4]).*";
        findPhotos(PHOTO_NAMES, regexNight18to24);

        System.out.println("=====CHICAGO_NIGHT_18:00_TO_24:00_PICTURES=====");
        String regexChicagoNight = "Chicago\\\\\\d{8}_(1[8|9]|2[0-4]).*";
        findPhotos(PHOTO_NAMES, regexChicagoNight);

        System.out.println("=====JPG_PNG_PICTURES=====");
        String regexJpgPng = ".+\\.(jpg|png)";
        findPhotos(PHOTO_NAMES, regexJpgPng);
    }

    public static void findPhotos(String[] photos, String regexFilter) {
        for(String photo : photos){
            if (photo.matches(regexFilter))
                System.out.println(photo);
        }
        System.out.println();
    }
}
