package telran.album.dao;

import telran.album.model.Photo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class AlbumImpl implements Album {

    private Photo[] photos;
    private int size;

    public AlbumImpl(int capacity) {
        photos = new Photo[capacity];
    }

    @Override
    public boolean addPhoto(Photo photo) {
        if (photo == null ||
                size == photos.length ||
                getPhotoFromAlbum(photo.getPhotoId(), photo.getAlbumId()) != null) {
            return false;
        }

        int index = Arrays.binarySearch(photos, 0, size, photo);
        if (index < 0) index = -index - 1;
        System.arraycopy(photos, index, photos, index + 1, size - index);
        photos[index] = photo;
        size++;
        return true;
    }

    @Override
    public boolean removePhoto(int photoId, int albumId) {
        Photo pattern = new Photo(albumId, photoId, null, null, null);
        for (int i = 0; i < size; i++) {
            if (photos[i].equals(pattern)) {
                System.arraycopy(photos, i + 1, photos, i, size - i - 1);
                photos[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePhoto(int photoId, int albumId, String url) {
        Photo photo = getPhotoFromAlbum(photoId, albumId);
        if (photo == null) return false;

        photo.setUrl(url);
        return true;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        Photo pattern = new Photo(albumId, photoId, null, null, null);
        for (int i = 0; i < size; i++) {
            if (photos[i].equals(pattern)) {
                return photos[i];
            }
        }
        return null;
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albumId) {
        ArrayList<Photo> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumId() == albumId) {
                result.add(photos[i]);
            }
        }

        return result.toArray(new Photo[0]);
    }

    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        ArrayList<Photo> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            boolean isAfterBotBorderInclusive = photos[i].getDate().toLocalDate().compareTo(dateFrom) >= 0;
            boolean isBeforeTopBorderInclusive = photos[i].getDate().toLocalDate().compareTo(dateTo) <= 0;
            if (isAfterBotBorderInclusive && isBeforeTopBorderInclusive) {
                result.add(photos[i]);
            }
        }

        return result.toArray(new Photo[0]);
    }

    @Override
    public int size() {
        return size;
    }
}
