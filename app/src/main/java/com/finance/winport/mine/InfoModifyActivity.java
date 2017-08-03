package com.finance.winport.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.mine.event.ModifyEvent;
import com.finance.winport.mine.framgent.ModifyNickNameFragment;
import com.finance.winport.mine.framgent.ModifyPhoneFragment;
import com.finance.winport.mine.framgent.ModifySignFragment;

public class InfoModifyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_modify);
        ModifyEvent.InfoType type = (ModifyEvent.InfoType) getIntent().getSerializableExtra("type");
        handleType(type);
    }

    private void handleType(ModifyEvent.InfoType type) {
        BaseFragment fragment = null;
        switch (type) {
            case NICK_NAME:
                fragment = new ModifyNickNameFragment();
                break;
            case SIGN:
                fragment = new ModifySignFragment();
                break;
            case PHONE:
                fragment = new ModifyPhoneFragment();
                break;
//            case CONCERN_TYPE:
//                fragment = new ModifyNickNameFragment();
//                break;

        }
        if (fragment != null) {
            fragment.setArguments(getIntent().getExtras());
            pushFragment(fragment);
        }
    }
}
