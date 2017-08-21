package com.finance.winport.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.webkit.WebView;

import static android.view.KeyEvent.ACTION_UP;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

/**
 * Created by xzw on 2017/8/8.
 */

public class CustomWebView extends WebView {
    private GestureDetector gestureDetector;
    private OnScrollListener onScrollListener;
    private int lastScrollY;
    private int delayMillis = 10;
    private boolean isFling;
    private int action;
    private boolean isStopped = true;
    private boolean isScrollChanged;

    public CustomWebView(Context context) {
        this(context, null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, gestureDetectorListener);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        isScrollChanged = true;
        int height = (int) Math.floor(this.getContentHeight() * this.getScale());
        int webViewHeight = this.getHeight();
        int scrollY = this.getScrollY() + webViewHeight;
        int deltaY = t - oldt;
        if (scrollY + ViewConfiguration.getTouchSlop() >= height || t == 0) {
            if (t == 0) deltaY = 0;
            if (onScrollListener != null) {
                onScrollListener.onScrollEdge(deltaY);
            }
        } else {
            if (onScrollListener != null) {
                onScrollListener.onScroll(deltaY);
            }
        }
        if (t == 0 || scrollY >= height) {
            isStopped = true;
            isScrollChanged = false;
        } else {
            if (Math.abs(deltaY) <= 1) {
                isStopped = true;
                isScrollChanged = false;
            }
        }
//        Log.d("TAG", "onScrollChanged:" + "isFling=" + isFling + ",webViewHeight=" + webViewHeight + ",height=" + height + ",scrollY=" + scrollY + ",l=" + l + "," + "t=" + t + "," + "oldl=" + oldl + "," + "oldt=" + oldt + ",deltaY=" + deltaY);
        if (!isFling) return;
        if (scrollY >= height || t == 0 || (Math.abs(deltaY) <= 1)) {
            if (onScrollListener != null) {
                scrollY = t == 0 ? 0 : getScrollY();
                onScrollListener.onScrollIdle(scrollY);
            }
            if (isFling) isFling = false;
        } else {
            if (action == ACTION_DOWN) {
                isStopped = true;
                isScrollChanged = false;
            }
            if (onScrollListener != null) {
//                onScrollListener.onScroll(deltaY);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        action = ev.getAction();
        if (!isStopped && onScrollListener != null) {
//            onScrollListener.onScroll(lastScrollY - this.getScrollY());
        }
        switch (action) {
            case ACTION_DOWN:
                break;
            case ACTION_MOVE:
                if (isScrollChanged) {
                    isStopped = false;
                }
                break;
            case ACTION_UP:
                if (isStopped) {
                    if (onScrollListener != null) {
                        onScrollListener.onScrollIdle(getScrollY());
                    }
                } else {
                    handler.sendMessageDelayed(handler.obtainMessage(), delayMillis);
                }
                break;
        }
        gestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = CustomWebView.this.getScrollY();

            //此时的距离和记录下的距离不相等，在隔delayMillis毫秒给handler发送消息
            if (!isFling || !isStopped) {
                int deltaY = lastScrollY - scrollY;
                if (lastScrollY != scrollY) {
                    lastScrollY = scrollY;
                    handler.sendMessageDelayed(handler.obtainMessage(), delayMillis);
                    if (onScrollListener != null) {
//                        onScrollListener.onScroll(deltaY);
                    }
                } else {
                    isStopped = true;
                    isScrollChanged = false;
                    if (onScrollListener != null) {
                        onScrollListener.onScrollIdle(getScrollY());
                    }
                }
            }

        }
    };

    private SimpleOnGestureListener gestureDetectorListener = new SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            isFling = true;
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };


    public interface OnScrollListener {
        /**
         * @param deltaY
         */
        void onScroll(int deltaY);

        void onScrollIdle(int scrollY);

        void onScrollEdge(int deltaY);
    }

}
