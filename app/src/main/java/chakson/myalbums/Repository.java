package chakson.myalbums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chakson.myalbums.model.Album;
import chakson.myalbums.model.Photo;

/**
 * Created by Nikola Carija on 5/25/16.
 */
public class Repository {

    private static Repository instance = null;

    private List<Album> albums;
    private Map<Integer, List<Photo>> photos;

    private Repository() {
        albums = new ArrayList<>();
        photos = new HashMap<>();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Photo> getPhotos(int albumId) {
        return photos.get(albumId);
    }

    public void setPhotos(int albumId, List<Photo> photos) {
        this.photos.put(albumId, photos);
    }
}
