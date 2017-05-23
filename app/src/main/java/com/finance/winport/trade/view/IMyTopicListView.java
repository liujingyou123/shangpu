package com.finance.winport.trade.view;

import com.finance.winport.trade.model.MyTopicResponse;

/**
 * Created by liuworkmac on 17/5/23.
 */

public interface IMyTopicListView {
    void showTopics(MyTopicResponse response);

    void showMoreTopics(MyTopicResponse response);

    void onError();
}
