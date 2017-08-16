package com.finance.winport.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.net.URL;

/**
 * Created by xzw on 2017/8/9.
 */

public class HtmlTextView extends android.support.v7.widget.AppCompatTextView {
    public HtmlTextView(Context context) {
        this(context, null);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setMovementMethod(LinkMovementMethod.getInstance());
        ellipsize();
    }

    private void ellipsize() {
        post(new Runnable() {
            @Override
            public void run() {
                HtmlTextView htv = HtmlTextView.this;
                if (htv.getLineCount() > htv.getMaxLines()) {
                    int lineEndIndex = htv.getLayout().getLineEnd(htv.getMaxLines() - 1);
                    int length = htv.getText().toString().trim().length();
                    int end = 3;
                    if (length > lineEndIndex && lineEndIndex > end) {
                        String text = htv.getText().subSequence(0, lineEndIndex - end) + "...";
                        htv.setText(text);
                    }
                }
            }
        });
    }

    public void setHtml(CharSequence c) {
        setTextWithoutImage(Html.fromHtml(c.toString()));
        execute(c.toString());
        ellipsize();
    }

    public void setHtml(CharSequence c, boolean withImages) {
        if (TextUtils.isEmpty(c)) {
            setText(c);
        } else {
            String htmlBody = c.toString();
            if (!withImages) {
                htmlBody = c.toString().replaceAll("<img.+/(img)*>", "");
            }
            setTextWithoutImage(Html.fromHtml(htmlBody));
        }
        ellipsize();
    }


    private void setTextWithoutImage(Spanned text) {
        setText(text);
    }

    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight());
            return drawable;
        }
    };

    private void execute(String s) {
        AsyncTask<String, Integer, Spanned> task = new AsyncTask<String, Integer, Spanned>() {
            @Override
            protected Spanned doInBackground(String... params) {
                Spanned sp = Html.fromHtml(params[0], imgGetter, null);
                return sp;
            }

            @Override
            protected void onPostExecute(Spanned spanned) {
                HtmlTextView.this.setText(spanned);
            }
        };
        task.execute(s);
    }

}
