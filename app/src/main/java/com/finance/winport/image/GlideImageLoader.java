package com.finance.winport.image;

import android.content.Context;
import android.widget.ImageView;

import com.finance.winport.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by liuworkmac on 17/5/12.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setBackgroundResource(R.drawable.default_image_logo);
        Batman.getInstance().fromNet((String) path, imageView);
    }
}
