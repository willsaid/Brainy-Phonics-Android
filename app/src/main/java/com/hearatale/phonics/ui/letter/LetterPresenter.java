package com.hearatale.phonics.ui.letter;

import com.hearatale.phonics.data.AppDataManager;
import com.hearatale.phonics.data.DataManager;
import com.hearatale.phonics.data.model.phonics.letters.LetterModel;
import com.hearatale.phonics.ui.base.activity.PresenterMVP;

import java.util.List;
import java.util.Map;

public class LetterPresenter extends PresenterMVP<ILetter> implements ILetterPresenter {

    DataManager mDataManager;

    LetterPresenter() {
        mDataManager = AppDataManager.getInstance();
    }

    Map<String, List<LetterModel>> getLetter() {
        return mDataManager.getLetters();
    }
}
