package chakson.myalbums.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import chakson.myalbums.Repository;
import chakson.myalbums.fragment.ImageSlideFragment;
import chakson.myalbums.model.Photo;

/**
 * Created by Nikola Carija on 5/26/16.
 */
public class ImageAdapter extends FragmentStatePagerAdapter {

    private int albumId;

    public ImageAdapter(FragmentManager fm, int albumId) {
        super(fm);
        this.albumId = albumId;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageSlideFragment.newInstance(albumId, position);
    }

    @Override
    public int getCount() {
        List<Photo> photos = Repository.getInstance().getPhotos(albumId);
        return photos == null ? 0 : photos.size();
    }
}
