package com.hearatale.phonics.ui.quiz_puzzle;

import com.hearatale.phonics.data.AppDataManager;
import com.hearatale.phonics.data.DataManager;
import com.hearatale.phonics.data.model.event.StarEvent;
import com.hearatale.phonics.data.model.phonics.letters.LetterModel;
import com.hearatale.phonics.data.model.phonics.letters.PuzzlePieceModel;
import com.hearatale.phonics.data.model.phonics.letters.WordModel;
import com.hearatale.phonics.data.model.typedef.DifficultyDef;
import com.hearatale.phonics.ui.base.activity.PresenterMVP;
import com.hearatale.phonics.utils.Config;
import com.hearatale.phonics.utils.Helper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizPuzzlePresenter extends PresenterMVP<IQuizPuzzle> implements IQuizPuzzlePresenter {

    private DataManager mDataManager;

    QuizPuzzlePresenter() {
        mDataManager = AppDataManager.getInstance();
    }

    private List<WordModel> mAllPossibleWord = new ArrayList<>();
    private LetterModel mLetter = new LetterModel();
    private List<WordModel> mAnswerWords = new ArrayList<>();
    private int mCurrentPosition = 0;


    @Override
    public void savePuzzlePiece(String displayLetter, PuzzlePieceModel puzzlePiece) {
        mDataManager.savePuzzlePiece(displayLetter, puzzlePiece);
    }

    @Override
    public void savePuzzlePiece(String displayLetter, List<PuzzlePieceModel> puzzlePieces) {
        mDataManager.savePuzzlePiece(displayLetter, puzzlePieces);
    }

    @Override
    public List<PuzzlePieceModel> getCompletedPuzzlePieces(String displayLetter) {
        return mDataManager.getCompletedPuzzlePieces(displayLetter);
    }

    @Override
    public void savePuzzleBase64(String displayLetter, String base64) {
        mDataManager.savePuzzleBase64(displayLetter, base64);
    }

    @Override
    public String getPuzzleBase64(String displayLetter) {
        return mDataManager.getPuzzleBase64(displayLetter);
    }

    @Override
    public LetterModel randomLetter(@DifficultyDef int diffDef) {
        return mDataManager.randomLetter(diffDef);
    }

    @Override
    public List<WordModel> getSelectedWords() {

        // TODO get selectedWords
        return new ArrayList<>();
    }

    @Override
    public List<WordModel> getSelectedWords(LetterModel letter, boolean puzzleRandom) {

        if (mLetter != letter) {
            mLetter = letter;
            mAllPossibleWord = mDataManager.getAllPossibleWords(mLetter);
            mAnswerWords = new ArrayList<>();
            mAnswerWords = letter.getPrimaryWords();
            mAnswerWords.addAll(letter.getQuizWords());
        }

        List<WordModel> selectedWords = new ArrayList<>();

        // get selectedWords puzzleRandom: true -> 4, false -> 3
        int totalWords = puzzleRandom ? 4 : 3;

        // add answerWord
        // get random answerWords
        WordModel answerWord;
        if (puzzleRandom) {
            answerWord = Helper.randomItems(mAnswerWords);
        } else {
            if (mCurrentPosition >= mAnswerWords.size()) {
                mCurrentPosition = 0;
            }
            answerWord = mAnswerWords.get(mCurrentPosition);
            mCurrentPosition++;
        }
        answerWord.setAnswer(true);
        selectedWords.add(answerWord);


        // add wrong possible word
        while (selectedWords.size() < totalWords) {

//            Collections.shuffle(mAllPossibleWord);

            WordModel word = Helper.randomItems(mAllPossibleWord);
//            if (!selectedWords.contains(word)) {
//                selectedWords.add(word);
//            }

            boolean find = false;
            for (WordModel w : selectedWords) {
                if (w.getText().equals(word.getText())) {
                    find = true;
                    break;
                }
            }

            if (!find) {
                // prevent prev answer set is true
                word.setAnswer(false);
                selectedWords.add(word);
            }

        }

        // shuffle selectedWords
        Collections.shuffle(selectedWords);
        return selectedWords;
    }

    @Override
    public int getStarConsecutive(String displayLetter) {
        return mDataManager.getStarConsecutive(displayLetter);
    }

    @Override
    public void saveStarConsecutive(String displayLetter, int starConsecutive) {
        int currentStarConsecutive = getStarConsecutive(displayLetter);

        if (starConsecutive > currentStarConsecutive && starConsecutive >= Config.MIN_STAR_CONSECUTIVE) {
            mDataManager.saveStarConsecutive(displayLetter, starConsecutive);

            boolean isCompleted = mDataManager.isPuzzleCompleted(displayLetter);
            if (isCompleted) {
                EventBus.getDefault().post(new StarEvent(displayLetter, starConsecutive));
            }

        }
    }

}
