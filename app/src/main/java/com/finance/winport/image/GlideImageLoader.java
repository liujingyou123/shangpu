package com.finance.winport.image;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by liuworkmac on 17/5/12.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Batman.getInstance().fromNet((String) path, imageView);
    }
}
