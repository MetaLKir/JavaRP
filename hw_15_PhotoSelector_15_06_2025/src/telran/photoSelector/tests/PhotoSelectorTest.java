package telran.photoSelector.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.photoSelector.tools.PhotoSelector.*;

class PhotoSelectorTest {

    private static final String[] pictures = {
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

    @Test
    void testAllEuropePictures(){
        String regex = "^(Paris|London).*";
        String[] expected = {
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
        };
        String[] actual = selectPictures(pictures, regex);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testAllAutumnPictures(){
        String regex = ".*\\\\\\d{4}(09|10|11).*";
        String[] expected = {
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"
        };
        String[] actual = selectPictures(pictures, regex);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testAllSpring2015Pictures(){
        String regex = ".*\\\\2015(03|04|05).*";
        String[] expected = {
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_110023.gif",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "Chicago\\20150301_120001.png"
        };
        String[] actual = selectPictures(pictures, regex);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testAllNightPictures(){
        // from 18 to 24
        String regex = ".*\\\\\\d{8}_(1[8|9]|2[0-4]).*";
        String[] expected = {
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20140601_211035.gif",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"
        };
        String[] actual = selectPictures(pictures, regex);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testAllChicagoNightPictures(){
        String regex = "Chicago\\\\\\d{8}_(1[8|9]|2[0-4]).*";
        String[] expected = {
                "Chicago\\20151111_232000.png"
        };
        String[] actual = selectPictures(pictures, regex);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testAllJpgPngPictures(){
        String regex = ".+\\.(jpg|png)";
        String[] expected = {
                "Paris\\20140101_090000.jpg",
                "Paris\\20140201_121005.jpg",
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "London\\20140205_090000.jpg",
                "London\\20140205_121005.jpg",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20150301_120001.png",
                "Chicago\\20151111_232000.png"
        };
        String[] actual = selectPictures(pictures, regex);

        assertArrayEquals(expected, actual);
    }
}
