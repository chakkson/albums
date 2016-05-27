package chakson.myalbums.rest;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import chakson.myalbums.Constants;
import chakson.myalbums.model.Album;
import chakson.myalbums.model.Photo;

/**
 * Created by Nikola Carija on 5/25/16.
 */
public class RestApi {

    public static List<Album> getAlbums(int userId) {
        String link = Constants.Rest.ALBUMS + "?userId=" + userId;
        String response = HttpUtils.getAsString(link);

        List<Album> albums = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                Album album = new Album();
                JSONObject jsonObject = json.getJSONObject(i);
                album.setId(jsonObject.getInt(Constants.Keys.ID));
                album.setUserId(jsonObject.getInt(Constants.Keys.USER_ID));
                album.setTitle(jsonObject.getString(Constants.Keys.TITLE));
                album.setThumbnail(getAlbumThumbnail(album.getId()));
                albums.add(album);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return albums;
    }

    private static Bitmap getAlbumThumbnail(int albumId) {
        String link = Constants.Rest.PHOTOS + "?albumId=" + albumId;
        String response = HttpUtils.getAsString(link);
        Bitmap thumbnail = null;
        try {
            JSONArray json = new JSONArray(response);
            if (json.length() > 0) {
                thumbnail = HttpUtils.getAsBitmap(json.getJSONObject(0).getString(Constants.Keys.URL_THUMBNAIL));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thumbnail;
    }

    public static List<Photo> getPhotos(int albumId, int maxPhotos) {
        String link = Constants.Rest.PHOTOS + "?albumId=" + albumId;
        String response = HttpUtils.getAsString(link);

        List<Photo> photos = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(response);
            for (int i = 0; i < Math.min(json.length(), maxPhotos); i++) {
                Photo photo = new Photo();
                JSONObject jsonObject = json.getJSONObject(i);
                photo.setId(jsonObject.getInt(Constants.Keys.ID));
                photo.setAlbumId(jsonObject.getInt(Constants.Keys.ALBUM_ID));
                photo.setTitle(jsonObject.getString(Constants.Keys.TITLE));
                photo.setImage(HttpUtils.getAsBitmap(jsonObject.getString(Constants.Keys.URL)));
                photo.setThumbnail(HttpUtils.getAsBitmap(jsonObject.getString(Constants.Keys.URL_THUMBNAIL)));
                photos.add(photo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photos;
    }
}
