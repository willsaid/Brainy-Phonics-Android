package com.hearatale.phonics.ui.sentence;

import com.hearatale.phonics.data.AppDataManager;
import com.hearatale.phonics.data.DataManager;
import com.hearatale.phonics.ui.base.activity.PresenterMVP;

public class SentencePresenter extends PresenterMVP<ISentence> implements ISentencePresenter {

    private DataManager mDataManager;

    SentencePresenter() {
        mDataManager = AppDataManager.getInstance();
    }

}
