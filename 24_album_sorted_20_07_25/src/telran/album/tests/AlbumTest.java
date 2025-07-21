package telran.album.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.album.dao.Album;
import telran.album.dao.AlbumImpl;
import telran.album.model.Photo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    Album collectionOfAlbums;
    Photo[] photos = new Photo[6];
    LocalDateTime now = LocalDateTime.now();
    Comparator<Photo> comp = (p1, p2) -> {
        int res = Integer.compare(p1.getAlbumId(), p2.getAlbumId());
        return res != 0 ? res : Integer.compare(p1.getPhotoId(), p2.getPhotoId());
    };

    @BeforeEach
    void setUp() {
        collectionOfAlbums = new AlbumImpl(7);
        photos[0] = new Photo(1, 1, "title1", "url1", now.minusDays(7));
        photos[1] = new Photo(1, 2, "title2", "url2", now.minusDays(5).toLocalDate().atStartOfDay());
        photos[2] = new Photo(1, 3, "title3", "url3", now.minusDays(5).toLocalDate().atStartOfDay());
        photos[3] = new Photo(2, 1, "title1", "url1", now.minusDays(5).toLocalDate().atStartOfDay());
        photos[4] = new Photo(2, 4, "title4", "url4", LocalDateTime.of(now.minusDays(2).toLocalDate(), LocalTime.MAX));
        photos[5] = new Photo(1, 4, "title4", "url4", LocalDateTime.of(now.minusDays(2).toLocalDate(), LocalTime.MAX));

        for (int i = 0; i < photos.length - 1; i++) {
            collectionOfAlbums.addPhoto(photos[i]);
        }
    }

    @Test
    void addPhoto() {
        assertTrue(collectionOfAlbums.addPhoto(photos[5]));
        assertEquals(photos[5], collectionOfAlbums.getPhotoFromAlbum(4, 1));
        assertEquals(6, collectionOfAlbums.size());
        assertFalse(collectionOfAlbums.addPhoto(photos[5]));
        assertFalse(collectionOfAlbums.addPhoto(null));
    }

    @Test
    void removePhoto() {
        assertTrue(collectionOfAlbums.removePhoto(1, 1));
        assertNull(collectionOfAlbums.getPhotoFromAlbum(1, 1));
        assertEquals(4, collectionOfAlbums.size());
        assertFalse(collectionOfAlbums.removePhoto(42, 42));
    }

    @Test
    void updatePhoto() {
        assertTrue(collectionOfAlbums.updatePhoto(1, 1, "newUrl"));
        assertEquals("newUrl", collectionOfAlbums.getPhotoFromAlbum(1, 1).getUrl());
    }

    @Test
    void getPhotoFromAlbum() {
        assertEquals(photos[0], collectionOfAlbums.getPhotoFromAlbum(1, 1));
        assertNull(collectionOfAlbums.getPhotoFromAlbum(42, 42));
    }

    @Test
    void getAllPhotoFromAlbum() {
        Photo[] actuals = collectionOfAlbums.getAllPhotoFromAlbum(2);
        Arrays.sort(actuals, comp);
        Photo[] expecteds = {photos[3],photos[4]};
        assertArrayEquals(expecteds, actuals);

    }

    @Test
    void getPhotoBetweenDate() {
        LocalDate ld = now.toLocalDate();
        collectionOfAlbums.addPhoto(photos[5]);
        Photo[] actuals = collectionOfAlbums.getPhotoBetweenDate(ld.minusDays(5), ld.minusDays(2));
        Arrays.sort(actuals, comp);
        Photo[] expecteds = {photos[1], photos[2], photos[5], photos[3], photos[4]};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    void size() {
        assertEquals(5, collectionOfAlbums.size());
    }
}