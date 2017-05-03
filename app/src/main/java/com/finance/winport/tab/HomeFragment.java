package com.finance.winport.tab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.CountDownButton;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 *
 *
 */

public class HomeFragment extends BaseFragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
