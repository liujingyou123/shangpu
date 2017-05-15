package com.finance.winport.map;

import android.view.View;

/**
 * Created by css_double on 17/5/4.
 */

public interface OnItemClickListener<T> {
    void onClick(int position, T data);
}
