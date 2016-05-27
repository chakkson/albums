package chakson.myalbums.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import chakson.myalbums.Constants;
import chakson.myalbums.R;
import chakson.myalbums.Repository;
import chakson.myalbums.model.Photo;

/**
 * Created by Nikola Carija on 5/26/16.
 */
public class ImageSlideFragment extends Fragment {

    public static ImageSlideFragment newInstance(int albumId, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.Keys.ALBUM_ID, albumId);
        bundle.putInt(Constants.Keys.ID, position);
        ImageSlideFragment instance = new ImageSlideFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slide, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageSlideImage);
        int albumId = getArguments().getInt(Constants.Keys.ALBUM_ID);
        int position = getArguments().getInt(Constants.Keys.ID);
        Photo photo = Repository.getInstance().getPhotos(albumId).get(position);
        imageView.setImageBitmap(photo.getImage());
        return view;
    }
}
