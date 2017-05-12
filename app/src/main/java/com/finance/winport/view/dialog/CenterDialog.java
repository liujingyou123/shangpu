package com.finance.winport.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by css_double on 17/5/2.
 */

public class CenterDialog extends XDialog {

    public CenterDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void show() {
        setType(TYPE.TYPE_FROM_CENTER);
        super.show();
    }
}
