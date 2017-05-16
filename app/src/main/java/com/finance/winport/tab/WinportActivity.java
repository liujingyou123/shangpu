package com.finance.winport.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.tab.fragment.MineWinportFragment;
import com.finance.winport.tab.fragment.OffShelfFragment;
import com.finance.winport.tab.fragment.ScanWinportFragment;

public class WinportActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winport);
        int type = getIntent().getIntExtra("type", -1);
        handleTag(type);
    }

    private void handleTag(int type) {
        switch (type) {
            case 1:// 我发布的
                BaseFragment release = new MineWinportFragment();
                release.setArguments(getIntent().getExtras());
                pushFragment(release);
                break;
            case 2:// 最近浏览、收藏、约看
            case 3:
            case 4:
                BaseFragment scanf = new ScanWinportFragment();
                scanf.setArguments(getIntent().getExtras());
                pushFragment(scanf);
                break;
            case 5://下架
                BaseFragment dropOff = new OffShelfFragment();
                dropOff.setArguments(getIntent().getExtras());
                pushFragment(dropOff, false);
                break;
        }
    }

    public void pushFragment(BaseFragment fragment, boolean addToBackStack) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();
        if (count >= 1) {
            ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        }
        ft.replace(R.id.rl_fragment_content, fragment, tag);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }
}
