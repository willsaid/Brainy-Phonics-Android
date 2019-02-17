package com.hearatale.phonics.ui.bank;

import com.hearatale.phonics.data.AppDataManager;
import com.hearatale.phonics.data.DataManager;
import com.hearatale.phonics.ui.base.activity.PresenterMVP;

public class BankPresenter extends PresenterMVP<IBank> implements IBankPresenter {
    DataManager mDataManager;

    BankPresenter(){
        mDataManager = AppDataManager.getInstance();
    }

    public int getTotalGoldCount(String appFeature){
        return mDataManager.getTotalGoldCount(appFeature);
    }

    public int getTotalSilverCount(String appFeature){
        return mDataManager.getTotalSilverCount(appFeature);
    }

}
