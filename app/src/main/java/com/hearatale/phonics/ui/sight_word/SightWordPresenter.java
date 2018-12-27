package com.hearatale.phonics.ui.sight_word;

import android.support.v7.widget.AppCompatImageView;

import com.hearatale.phonics.data.AppDataManager;
import com.hearatale.phonics.data.DataManager;
import com.hearatale.phonics.data.model.phonics.SightWordModel;
import com.hearatale.phonics.data.model.typedef.SightWordsCategoryDef;
import com.hearatale.phonics.ui.base.activity.PresenterMVP;

import java.util.List;

public class SightWordPresenter extends PresenterMVP<ISightWord> implements ISightWordPresenter {

    private DataManager mDataManager;

    SightWordPresenter() {
        mDataManager = AppDataManager.getInstance();
    }

    @Override
    public List<SightWordModel> getSightWords(@SightWordsCategoryDef int category) {
        return mDataManager.getSightWords(category);
    }
}
