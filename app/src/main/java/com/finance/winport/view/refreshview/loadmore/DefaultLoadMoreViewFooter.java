/*
Copyright 2015 Chanven

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.finance.winport.view.refreshview.loadmore;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;

/**
 * default load more view
 */
public class DefaultLoadMoreViewFooter implements ILoadMoreViewFactory {

    @Override
    public ILoadMoreView madeLoadMoreView() {
        return new LoadMoreHelper();
    }

    private class LoadMoreHelper implements ILoadMoreView {

        protected View footerView;
        protected TextView footerTv;
//        protected ProgressBar footerBar;

        protected OnClickListener onClickRefreshListener;

        private ImageView imgLeft,imgRight;

        @Override
        public void init(FootViewAdder footViewHolder, OnClickListener onClickRefreshListener) {
            footerView = footViewHolder.addFootView(R.layout.ptr_loadmore_default_footer);
            footerTv = (TextView) footerView.findViewById(R.id.loadmore_default_footer_tv);
//            footerBar = (ProgressBar) footerView.findViewById(R.id.loadmore_default_footer_progressbar);
            imgLeft = (ImageView) footerView.findViewById(R.id.ptr_img_left);
            imgRight = (ImageView) footerView.findViewById(R.id.ptr_img_right);
            this.onClickRefreshListener = onClickRefreshListener;
            showNormal();
        }

        @Override
        public void showNormal() {
            footerTv.setText(R.string.cube_ptr_load_more);
//            footerBar.setVisibility(View.GONE);
            imgLeft.setVisibility(View.GONE);
            imgRight.setVisibility(View.GONE);
            imgRight.clearAnimation();
            imgLeft.clearAnimation();
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showLoading() {
            footerTv.setText(R.string.cube_ptr_refreshing);
//            footerBar.setVisibility(View.VISIBLE);
            imgLeft.setVisibility(View.VISIBLE);
            imgRight.setVisibility(View.VISIBLE);
            buildAnimation();
            footerView.setOnClickListener(null);
        }

        @Override
        public void showFailure() {
            footerTv.setText(R.string.cube_ptr_load_fail);
//            footerBar.setVisibility(View.GONE);
            imgLeft.setVisibility(View.GONE);
            imgRight.setVisibility(View.GONE);
            imgRight.clearAnimation();
            imgLeft.clearAnimation();
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showNomore() {
            footerTv.setText(R.string.cube_ptr_no_more);
//            footerBar.setVisibility(View.GONE);
            imgLeft.setVisibility(View.GONE);
            imgRight.setVisibility(View.GONE);
            imgRight.clearAnimation();
            imgLeft.clearAnimation();
            footerView.setOnClickListener(null);
        }

        @Override
        public void showEmpty(){
            footerTv.setText(R.string.cube_ptr_empty);
//            footerBar.setVisibility(View.GONE);
            imgLeft.setVisibility(View.GONE);
            imgRight.setVisibility(View.GONE);
            imgRight.clearAnimation();
            imgLeft.clearAnimation();
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void setFooterVisibility(boolean isVisible) {
            footerView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
        private void buildAnimation() {
//        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        mFlipAnimation.setInterpolator(new LinearInterpolator());
//        mFlipAnimation.setDuration(mRotateAniTime);
//        mFlipAnimation.setFillAfter(true);
//
//        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
//        mReverseFlipAnimation.setDuration(mRotateAniTime);
//        mReverseFlipAnimation.setFillAfter(true);

            ObjectAnimator anim4 = ObjectAnimator.ofFloat(imgLeft,
                    "translationX", 0f,380f);

            ObjectAnimator anim3 = ObjectAnimator.ofFloat(imgLeft,
                    "translationX",  380f ,  300f,380f);
            anim3.setRepeatCount(-1);
            anim3.setInterpolator(new LinearInterpolator());



            ObjectAnimator anim1 = ObjectAnimator.ofFloat(imgRight,
                    "translationX", 0f,-380f);

            ObjectAnimator anim2 = ObjectAnimator.ofFloat(imgRight,
                    "translationX",  -380f ,  -300f,-380f);
            anim2.setRepeatCount(-1);
//        anim2.setInterpolator(new CycleInterpolator(0.0f));
            anim2.setInterpolator(new LinearInterpolator());

            AnimatorSet animSet = new AnimatorSet();
            animSet.play(anim3).after(anim4);
            animSet.play(anim4).with(anim1);
            animSet.play(anim2).after(anim1);
            animSet.setDuration(1000);
            animSet.start();

        }
    }

}
