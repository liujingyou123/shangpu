package com.finance.winport.view.BannerView;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.finance.winport.image.Batman;
import com.finance.winport.view.imagepreview.ImagePreviewActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jge on 16/3/10.
 */
public class ScorllBannerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> list;
    private ArrayList<String> toUrlList;
    private List<WeakReference<ImageView>> viewList;

    public ScorllBannerAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        viewList = new ArrayList<>();
    }

    public ScorllBannerAdapter(Context context, ArrayList<String> list, ArrayList<String> toUrlList) {
        this.context = context;
        this.list = list;
        this.toUrlList = toUrlList;
        viewList = new ArrayList<>();

    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView bannerItem = null;
        if (viewList != null && viewList.size() > 0) {
            if (viewList.get(0) != null) {
                bannerItem = viewList.remove(0).get();
            }
        } else {
            bannerItem = new ImageView(context);
        }


        String url = list.get(position);
        bannerItem.setOnClickListener(new View.OnClickListener() {
            int index = position;
            @Override
            public void onClick(View v) {

                if(toUrlList == null || toUrlList.size() == 0){
                    Intent intent = new Intent(context, ImagePreviewActivity.class);
                    intent.putExtra("pics", list);
                    intent.putExtra("index", index);
                    context.startActivity(intent);
//
//                    Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(toUrlList.get(position)));
//                    it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
//                    context.startActivity(it);
                }



            }
        });
//        bannerItem.setImageResource(R.mipmap.banner);

        Batman.getInstance().fromNet(url,bannerItem);
//        Batman.getInstance().fromNet(url,bannerItem, R.drawable.house_detail_default, R.drawable.house_detail_default);
//        Batman.getInstance().fromAsset("ad.png",bannerItem);
//        bannerItem.setScaleType(ImageView.ScaleType.FIT_XY);
        bannerItem.setScaleType(ImageView.ScaleType.CENTER_CROP);

        container.addView(bannerItem, new ViewPager.LayoutParams());
        return bannerItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
            if (viewList != null) {
                viewList.add(new WeakReference<ImageView>((ImageView) object));
            }
        }
    }

}
