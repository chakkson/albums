package chakson.myalbums.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nikola Carija on 5/25/16.
 */
public class HttpUtils {

    private static final int EOF = -1;
    private static final int BUFFER_SIZE = 1024;

    public static String getAsString(String link) {
        return get(link, new ResponseReader<String>() {
            @Override
            public String getResponse(InputStream inputStream) throws IOException {
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), BUFFER_SIZE);
                int c;
                while ((c = reader.read()) != EOF) {
                    sb.append((char) c);
                }
                return sb.toString();
            }
        });
    }

    public static Bitmap getAsBitmap(String link) {
        return get(link, new ResponseReader<Bitmap>() {
            @Override
            public Bitmap getResponse(InputStream inputStream) throws IOException {
                return BitmapFactory.decodeStream(inputStream);
            }
        });
    }

    public static <T> T get(String link, ResponseReader<T> responseReader) {
        T response = null;
        InputStream inputStream = null;
        try {
            inputStream = openStream(link);
            response = responseReader.getResponse(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    private static InputStream openStream(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        String redirection = urlConnection.getHeaderField("Location");
        if (redirection != null) return openStream(redirection);
        return new BufferedInputStream(urlConnection.getInputStream());
    }

    public interface ResponseReader<T> {
        T getResponse(InputStream inputStream) throws IOException;
    }
}
