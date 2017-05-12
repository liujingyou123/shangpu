package com.finance.winport.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.finance.winport.R;
import com.finance.winport.view.dialog.CenterDialog;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by css_double on 17/5/2.
 */

public class LoadingDialog extends CenterDialog {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_tip)
    TextView tvTip;

    private ScaleAnimation bigToSmall;
    private ScaleAnimation smallBig;

    private int [] resIds = new int[2];
    private int index = 1;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);

        setCanceledOnTouchOutside(false);

        resIds[0] = R.drawable.icon_loading_0;
        resIds[1] = R.drawable.icon_loading_1;

        bigToSmall();
    }

    @Override
    public void dismiss() {
        if (bigToSmall != null)
            bigToSmall.cancel();

        if (smallBig != null)
            smallBig.cancel();

        super.dismiss();
    }

    public void setTip(String tip) {
        tvTip.setText(tip);
    }

    private void smallToBig(){
        if (smallBig == null){
            smallBig = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            smallBig.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    System.out.println("getSmallToBig start");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    System.out.println("getSmallToBig end");
                    bigToSmall();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    System.out.println("getSmallToBig repeat");
                }
            });

            smallBig.setDuration(450);
            smallBig.setFillAfter(true);
            smallBig.setInterpolator(new AccelerateDecelerateInterpolator());
        }

        bigToSmall.reset();
        iv.startAnimation(smallBig);
    }

    private void bigToSmall(){
        if (bigToSmall == null){
            bigToSmall = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            bigToSmall.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    System.out.println("getBigToSmall start");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    System.out.println("getBigToSmall end");

                    iv.setImageResource(resIds[index]);

                    index = (index == 0 ? 1 : 0);

                    smallToBig();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    System.out.println("getBigToSmall repeat");
                }
            });

            bigToSmall.setFillAfter(true);
            bigToSmall.setDuration(450);
            bigToSmall.setInterpolator(new AccelerateDecelerateInterpolator());
        }

        bigToSmall.reset();
        iv.startAnimation(bigToSmall);
    }
}
