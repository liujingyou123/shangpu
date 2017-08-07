package com.finance.winport.util;

import android.util.SparseArray;

/**
 * Created by xzw on 16/9/2.
 */
public class ListViewUtils {

    public static int getScrollY(int mCurrentFirstVisibleItem, SparseArray<ItemRecord> recordList) {
        int height = 0;
        for (int i = 0; i < mCurrentFirstVisibleItem; i++) {
            ItemRecord itemRecord = recordList.get(i);
            if (itemRecord == null) continue;
            height += itemRecord.height;
        }
        ItemRecord itemRecord = recordList.get(mCurrentFirstVisibleItem);
        if (null == itemRecord) {
            itemRecord = new ItemRecord();
        }
        return height - itemRecord.top;
    }

    public static class ItemRecord {
        public int height = 0;
        public int top = 0;
    }
}
