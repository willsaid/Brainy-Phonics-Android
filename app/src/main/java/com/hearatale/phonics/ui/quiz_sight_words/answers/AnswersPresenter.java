package com.hearatale.phonics.ui.quiz_sight_words.answers;

import android.support.annotation.NonNull;

import com.hearatale.phonics.data.AppDataManager;
import com.hearatale.phonics.data.DataManager;
import com.hearatale.phonics.data.model.phonics.SightWordModel;
import com.hearatale.phonics.data.model.typedef.SightWordsCategoryDef;
import com.hearatale.phonics.service.AudioPlayerHelper;
import com.hearatale.phonics.ui.base.activity.PresenterMVP;
import com.hearatale.phonics.ui.base.fragment.FragmentPresenterMVP;
import com.hearatale.phonics.utils.Config;

import java.util.ArrayList;
import java.util.List;

public class AnswersPresenter extends FragmentPresenterMVP<IAnswers> implements IAnswersPresenter {
    private static final int COINS_TRUCK = 125;

    DataManager mDataManager;

    AnswersPresenter() {
        mDataManager = AppDataManager.getInstance();
    }

    @NonNull
    public List<SightWordModel> getAllWords(@SightWordsCategoryDef int category) {
        return new ArrayList<>(mDataManager.getSightWords(category));
    }

    @NonNull
    private String individualAudioFilePath(@SightWordsCategoryDef int category, SightWordModel sightWord) {
        String prefixFolder = "";
        if (category == SightWordsCategoryDef.PRE_K) {
            prefixFolder = "Pre-K Sight Words";
        } else {
            prefixFolder = "Kindergarten Sight Words";
        }
        return Config.AUDIO_ROOT_PATH + prefixFolder + "/Individual Words/" + sightWord.getText().toLowerCase();
    }

    public void playAudio(@SightWordsCategoryDef int category, SightWordModel sightWord) {
        String path = individualAudioFilePath(category, sightWord);
        AudioPlayerHelper.getInstance().playAudio(path);
    }

    public void increaseTotalGoldCoins() {
        int totalGoldCount = getTotalGoldCount() + 1;
        int totalSilverCoins = getTotalSilverCount();
        int totalCoins = totalGoldCount + (totalSilverCoins / 2);
        if (checkResetCoin(totalSilverCoins, totalCoins)) return;
        mDataManager.setTotalGoldCount(totalGoldCount);
    }

    public void increaseTotalSilverCoins() {
        int totalSilverCoins = mDataManager.getTotalSilverCount() + 1;
        int totalCoins = (totalSilverCoins / 2) + getTotalGoldCount();
        if (checkResetCoin(totalSilverCoins, totalCoins)) return;
        mDataManager.setTotalSilverCount(totalSilverCoins);
    }

    private boolean checkResetCoin(int totalSilverCount, int totalCoins) {
        int remainingSliver = totalSilverCount % 2;
        int trucks = totalCoins / COINS_TRUCK;
        if (trucks >= 10 && (totalCoins % COINS_TRUCK > 0 || remainingSliver > 0)) {
            //Reset coin
            resetCoins();
            return true;
        }
        return false;
    }


    public int getTotalGoldCount() {
        return mDataManager.getTotalGoldCount();
    }

    public int getTotalSilverCount() {
        return mDataManager.getTotalSilverCount();
    }

    public void resetCoins() {
        mDataManager.setTotalGoldCount(0);
        mDataManager.setTotalSilverCount(0);
    }
}
