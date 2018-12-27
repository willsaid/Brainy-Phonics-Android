package com.hearatale.phonics.ui.quiz;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import com.hearatale.phonics.data.AppDataManager;
import com.hearatale.phonics.data.DataManager;
import com.hearatale.phonics.data.model.phonics.SectionQuiz;
import com.hearatale.phonics.data.model.phonics.letters.LetterModel;
import com.hearatale.phonics.data.model.phonics.letters.PuzzlePieceModel;
import com.hearatale.phonics.data.model.typedef.DifficultyDef;
import com.hearatale.phonics.data.model.typedef.QuizLettersDef;
import com.hearatale.phonics.ui.base.activity.PresenterMVP;
import com.hearatale.phonics.utils.Config;
import com.hearatale.phonics.utils.Helper;
import com.hearatale.phonics.utils.ImageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizPresenter extends PresenterMVP<IQuiz> implements IQuizPresenter {

    private DataManager mDataManager;

    QuizPresenter() {
        mDataManager = AppDataManager.getInstance();
    }

    private void addLetterToListSection(LetterModel letter, List<SectionQuiz> dataQuiz, List<SectionQuiz> listNotComplete, String key) {
        if (isPuzzleCompleted(letter)) {
            String displayLetter = letter.getSourceLetter() + "-" + letter.getSoundId();
            String puzzleBase64 = mDataManager.getPuzzleBase64(displayLetter);
            Bitmap bitmap = ImageHelper.convert(puzzleBase64);
            dataQuiz.add(new SectionQuiz(letter, bitmap));
        } else {
            listNotComplete.add(new SectionQuiz(letter));
        }
    }

    private boolean isPuzzleCompleted(LetterModel letter) {
        return mDataManager.isPuzzleCompleted(letter);
    }

    @Override
    public void getAllLetter() {
        if (mView == null) {
            return;
        }

        Map<String, List<LetterModel>> mapLetter = mDataManager.getLetters();
        List<LetterModel> data = new ArrayList<>();

        // multiItem
        List<SectionQuiz> dataQuiz = new ArrayList<>();

        List<SectionQuiz> listNotComplete = new ArrayList<>();
        for (String key : mapLetter.keySet()) {
            if (mView.getDifficulty() == DifficultyDef.EASY) {
                LetterModel letter = mapLetter.get(key).get(0);
                addLetterToListSection(letter, dataQuiz, listNotComplete, key);
            } else {
                for (LetterModel letterModel : mapLetter.get(key)) {
                    addLetterToListSection(letterModel, dataQuiz, listNotComplete, key);
                }
            }

        }
        if (dataQuiz.size() > 0) {
            dataQuiz.add(new SectionQuiz(true, ""));
        }
        dataQuiz.addAll(listNotComplete);

        mView.updateDataQuiz(dataQuiz);
    }

    @Override
    public boolean isPuzzleCompleted(String sourceSoundId) {
        return mDataManager.isPuzzleCompleted(sourceSoundId);
    }
}
