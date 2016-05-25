package chakson.myalbums.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import chakson.myalbums.R;
import chakson.myalbums.Test;
import chakson.myalbums.adapter.AlbumAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView albumsView = (ListView) findViewById(R.id.listAlbums);
        if (albumsView != null) {
            AlbumAdapter albumAdapter = new AlbumAdapter(this, R.layout.album_row, Test.getAll());
            albumsView.setAdapter(albumAdapter);
        }
    }
}
