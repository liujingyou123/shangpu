package com.finance.winport.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzw on 2017/8/9.
 */

public class HtmlTextView extends android.support.v7.widget.AppCompatTextView {
    private final List<EllipsizeListener> ellipsizeListeners = new ArrayList<EllipsizeListener>();
    private boolean isEllipsized;
    private boolean isStale = true;
    private boolean programmaticChange;
    private String fullText;
    private float lineSpacingMultiplier = 1.0f;
    private float lineAdditionalVerticalPadding = 0.0f;
    private static String ELLIPSIS = "...";

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
    }

    public interface EllipsizeListener {
        void ellipsizeStateChanged(boolean ellipsized);
    }

    public void addEllipsizeListener(EllipsizeListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        ellipsizeListeners.add(listener);
    }

    public void removeEllipsizeListener(EllipsizeListener listener) {
        ellipsizeListeners.remove(listener);
    }

    public boolean isEllipsized() {
        return isEllipsized;
    }

    @Override
    public void setLineSpacing(float add, float mult) {
        this.lineAdditionalVerticalPadding = add;
        this.lineSpacingMultiplier = mult;
        super.setLineSpacing(add, mult);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        if (!programmaticChange) {
            fullText = text.toString();
            isStale = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isStale) {
            resetText();
        }
        super.onDraw(canvas);
    }

    public int getMaxLines() {
        Class<TextView> textViewClassInstance = TextView.class;
        try {
            Field MaxMode = textViewClassInstance.getDeclaredField("mMaxMode");
            MaxMode.setAccessible(true);
            int mMaxMode = MaxMode.getInt(this);
            Field Maximum = textViewClassInstance.getDeclaredField("mMaximum");
            Maximum.setAccessible(true);
            int mMaximum = Maximum.getInt(this);
            Field LINES = textViewClassInstance.getDeclaredField("LINES");
            LINES.setAccessible(true);
            int mLINES = LINES.getInt(this);
            return mMaxMode == mLINES ? mMaximum : -1;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void resetText() {
        int maxLines = getMaxLines();
        String workingText = fullText;
        boolean ellipsized = false;
        if (maxLines != -1) {
            Layout layout = createWorkingLayout(workingText);
            int originalLineCount = layout.getLineCount();
            if (originalLineCount > maxLines) {
                if (this.getEllipsize() == TextUtils.TruncateAt.START) {
                    workingText = fullText.substring(layout.getLineStart(originalLineCount - maxLines - 1)).trim();
                    while (createWorkingLayout(ELLIPSIS + workingText).getLineCount() > maxLines) {
                        int firstSpace = workingText.indexOf(' ');
                        if (firstSpace == -1) {
                            workingText = workingText.substring(1);
                        } else {
                            workingText = workingText.substring(firstSpace + 1);
                        }
                    }
                    workingText = ELLIPSIS + workingText;
                } else if (this.getEllipsize() == TextUtils.TruncateAt.END) {
                    workingText = fullText.substring(0, layout.getLineEnd(maxLines - 1));
                    while (createWorkingLayout(workingText + ELLIPSIS).getLineCount() > maxLines) {
                        int lastSpace = workingText.lastIndexOf(' ');
                        if (lastSpace == -1) {
                            workingText = workingText.substring(0, workingText.length() - 1);
                        } else {
                            workingText = workingText.substring(0, lastSpace);
                        }
                    }
                    workingText = workingText + ELLIPSIS;
                } else if (this.getEllipsize() == TextUtils.TruncateAt.MIDDLE) {
                    boolean shrinkLeft = false;
                    int firstOffset = layout.getLineEnd(maxLines / 2);
                    int secondOffset = layout.getLineEnd(originalLineCount - 1) - firstOffset + 1;
                    String firstWorkingText = fullText.substring(0, firstOffset).trim();
                    String secondWorkingText = fullText.substring(secondOffset).trim();
                    while (createWorkingLayout(firstWorkingText + ELLIPSIS + secondWorkingText).getLineCount() > maxLines) {
                        if (shrinkLeft) {
                            shrinkLeft = false;
                            int lastSpace = firstWorkingText.lastIndexOf(' ');
                            if (lastSpace == -1) {
                                firstWorkingText = firstWorkingText.substring(
                                        0, firstWorkingText.length() - 1);
                            } else {
                                firstWorkingText = firstWorkingText.substring(
                                        0, lastSpace);
                            }
                        } else {
                            shrinkLeft = true;
                            int firstSpace = secondWorkingText.indexOf(' ');
                            if (firstSpace == -1) {
                                secondWorkingText = secondWorkingText
                                        .substring(1);
                            } else {
                                secondWorkingText = secondWorkingText
                                        .substring(firstSpace + 1);
                            }
                        }
                    }
                    workingText = firstWorkingText + ELLIPSIS + secondWorkingText;
                }
                ellipsized = true;
            }
        }
        if (!workingText.equals(getText())) {
            programmaticChange = true;
            try {
                setText(workingText);
            } finally {
                programmaticChange = false;
            }
        }
        isStale = false;
        if (ellipsized != isEllipsized) {
            isEllipsized = ellipsized;
            for (EllipsizeListener listener : ellipsizeListeners) {
                listener.ellipsizeStateChanged(ellipsized);
            }
        }
    }

    private Layout createWorkingLayout(String workingText) {
        return new StaticLayout(workingText, getPaint(), getWidth() - getPaddingLeft() - getPaddingRight(),
                Layout.Alignment.ALIGN_NORMAL, lineSpacingMultiplier, lineAdditionalVerticalPadding, false);
    }

    public void setHtml(CharSequence c) {
        String s = c.toString().replaceAll("\n[ \t]*\n", "\n");
        setTextWithoutImage(Html.fromHtml(s));
        execute(s);
    }

    public void setHtml(CharSequence c, boolean withImages) {
//        String s = c.toString().replaceAll("\n[ \t]*\n", "\n");
        String s = c.toString();
        if (TextUtils.isEmpty(s)) {
            setText(s);
        } else {
            String htmlBody = s;
            if (!withImages) {
                htmlBody = s.replaceAll("(<(/)img>)|(<img.+?>)", "");
            }
            setTextWithoutImage(Html.fromHtml(htmlBody));
        }
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
