package chakson.myalbums.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import chakson.myalbums.Constants;
import chakson.myalbums.R;
import chakson.myalbums.Repository;
import chakson.myalbums.adapter.AlbumAdapter;
import chakson.myalbums.model.Album;
import chakson.myalbums.model.Photo;
import chakson.myalbums.rest.RestApi;

public class MainActivity extends AppCompatActivity {

    private ListView albumsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        albumsView = (ListView) findViewById(R.id.listAlbums);
        getAlbumsAndDisplay();
    }

    private void getAlbumsAndDisplay() {
        if (Repository.getInstance().getAlbums().isEmpty()){
            new DownloadAlbumsTask().execute(Constants.USER_ID);
        } else {
            displayAlbums();
        }
    }

    private void displayAlbums() {
        if (albumsView != null) {
            AlbumAdapter albumAdapter = new AlbumAdapter(this, R.layout.album_row, Repository.getInstance().getAlbums());
            albumsView.setAdapter(albumAdapter);
            albumsView.setOnItemClickListener(new AlbumClickedListener());
        }
    }

    private class DownloadAlbumsTask extends AsyncTask<Integer, Void, List<Album>> {
        @Override
        protected List<Album> doInBackground(Integer... params) {
            return RestApi.getAlbums(params[0]);
        }

        @Override
        protected void onPostExecute(List<Album> albums) {
            Repository.getInstance().setAlbums(albums);
            displayAlbums();
            for(final Album album : albums) {
                new DownloadPhotosTask().execute(album.getId(), Constants.MAX_PHOTOS_PER_ALBUM);
            }
        }
    }

    private class DownloadPhotosTask extends AsyncTask<Integer, Void, List<Photo>> {

        private int albumId;

        @Override
        protected List<Photo> doInBackground(Integer... params) {
            albumId = params[0];
            return RestApi.getPhotos(albumId, params[1]);
        }

        @Override
        protected void onPostExecute(List<Photo> photos) {
            Repository.getInstance().setPhotos(albumId, photos);
        }
    }

    private class AlbumClickedListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Album album = (Album) parent.getItemAtPosition(position);

            if (Repository.getInstance().getPhotos(album.getId()) == null) {
                Toast.makeText(getApplicationContext(), Constants.Messages.ALBUM_NOT_DOWNLOADED, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getApplicationContext(), ImageSlideActivity.class);
            intent.putExtra(Constants.Keys.ALBUM_ID, album.getId());
            startActivity(intent);
        }
    }
}
