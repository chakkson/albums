package chakson.myalbums;

import java.util.ArrayList;
import java.util.List;

import chakson.myalbums.model.Album;

/**
 * Created by Nikola Carija on 5/25/16.
 */
public class Test {

    public static List<Album> getAll() {
        List<Album> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Album("Album " + i));
        }
        return list;
    }
}
