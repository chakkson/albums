package chakson.myalbums.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chakson.myalbums.R;
import chakson.myalbums.model.Album;

/**
 * Created by Nikola Carija on 5/25/16.
 */
public class AlbumAdapter extends ArrayAdapter<Album> {

    public AlbumAdapter(Context context, int resource, List<Album> albums) {
        super(context, resource, albums);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View albumView = convertView;
        TextView titleView;
        // ImageView coverView;

        if (albumView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            albumView = inflater.inflate(R.layout.album_row, parent, false);
        }

        Album album = getItem(position);

        titleView = (TextView) albumView.findViewById(R.id.textAlbumTitle);
        // coverView = (ImageView) albumView.findViewById(R.id.imageAlbumCover);

        titleView.setText(album.getTitle());

        return albumView;
    }
}
