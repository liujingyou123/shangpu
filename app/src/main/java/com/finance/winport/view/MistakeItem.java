package com.finance.winport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.Tag;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/9.
 */

public class MistakeItem extends RelativeLayout {

    @BindView(R.id.tv_tagname)
    TextView tvTagname;
    @BindView(R.id.cb)
    CheckBox cb;
    private Context mContext;

    public MistakeItem(Context context) {
        super(context);
        init(context);
    }

    public MistakeItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_mistakeitem, this);
        ButterKnife.bind(this, this);
    }


    public void setTags(Tag tag) {
        if (tag != null) {
            cb.setTag(tag);
            tvTagname.setText(tag.getName());
        }
    }
}
