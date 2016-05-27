package chakson.myalbums;

/**
 * Created by Nikola Carija on 5/25/16.
 */
public class Constants {

    public static final int USER_ID = 2;
    public static final int MAX_PHOTOS_PER_ALBUM = 10;

    public static class Rest {
        private static final String API = "http://jsonplaceholder.typicode.com/";
        public static final String ALBUMS = API + "albums";
        public static final String PHOTOS = API + "photos";
        public static final String COMMENTS = API + "comments";
        public static final String POSTS = API + "posts";
    }

    public static class Keys {
        public static final String ID = "id";
        public static final String USER_ID = "userId";
        public static final String ALBUM_ID = "albumId";
        public static final String TITLE = "title";
        public static final String URL = "url";
        public static final String URL_THUMBNAIL = "thumbnailUrl";
    }

    public static class Messages {
        public static final String ALBUM_NOT_DOWNLOADED = "Album is still downloading.";
    }
}
