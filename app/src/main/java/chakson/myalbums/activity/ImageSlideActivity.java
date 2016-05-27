package chakson.myalbums.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import chakson.myalbums.Constants;
import chakson.myalbums.R;
import chakson.myalbums.adapter.ImageAdapter;

/**
 * Created by Nikola Carija on 5/26/16.
 */
public class ImageSlideActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slide);
        final ViewPager imageSlider = (ViewPager) findViewById(R.id.viewPagerImage);
        int albumId = getIntent().getIntExtra(Constants.Keys.ALBUM_ID, 0);
        ImageAdapter imageAdapter = new ImageAdapter(getSupportFragmentManager(), albumId);
        imageSlider.setAdapter(imageAdapter);
    }
}
