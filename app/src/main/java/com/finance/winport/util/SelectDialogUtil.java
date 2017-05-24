package com.finance.winport.util;

import android.content.Context;

import com.finance.winport.dialog.SelectionDialog;
import com.finance.winport.home.HomeFragment;
import com.finance.winport.home.model.ShopRequset;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by liuworkmac on 17/5/23.
 */

public class SelectDialogUtil {
    private static final SelectDialogUtil ourInstance = new SelectDialogUtil();

    public static SelectDialogUtil getInstance() {
        return ourInstance;
    }

    private Context mContext;

    private SelectDialogUtil() {
    }

    public void init(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    private SelectionDialog dialog;


    public void showDialog() {
        if (dialog == null) {
            dialog = new SelectionDialog(mContext);
            dialog.setOnSelectListener(new SelectionDialog.OnSelectListener() {
                @Override
                public void onSelect(ShopRequset request) {

                    EventBus.getDefault().post(request);
//                    selectionView.onCsUnClick();
//                    heardSelectView.onCsUnClick();
//                    if (request.rentList != null && request.rentList.size() > 0) {
//                        mRequest.rentList = request.rentList;
//                    } else {
//                        mRequest.rentList = null;
//                    }
//                    if (request.transferList != null && request.transferList.size() > 0) {
//                        mRequest.transferList = request.transferList;
//                    } else {
//                        mRequest.transferList = null;
//                    }
//                    if (request.areaList != null && request.areaList.size() > 0) {
//                        mRequest.areaList = request.areaList;
//                    } else {
//                        mRequest.areaList = null;
//                    }
//                    mRequest.width = request.width;
//                    if (request.featureTagList != null && request.featureTagList.size() > 0) {
//                        mRequest.featureTagList = request.featureTagList;
//                    } else {
//                        mRequest.featureTagList = null;
//                    }
//                    if (request.supportTagList != null && request.supportTagList.size() > 0) {
//                        mRequest.supportTagList = request.supportTagList;
//                    } else {
//                        mRequest.supportTagList = null;
//                    }

//                    mRequest.pageNumber = 1;
//                    mPresenter.getShopList(mRequest);
                }
            });
        }

        dialog.show();

//        if (!selectionDialog.isShowing()) {
//            if (sortPopupView != null && sortPopupView.isShowing()) {
//                sortPopupView.dismiss();
//            }
//            if (quyuPopupView != null && quyuPopupView.isShowing()) {
//                quyuPopupView.dismiss();
//            }
//            selectionDialog.show();
//            selectionView.onCsClick();
//            heardSelectView.onCsUnClick();
//        }
    }

    public boolean isShowing() {
        boolean ret = false;
        if (dialog != null && dialog.isShowing()) {
            ret = true;
        }

        return ret;
    }
}
