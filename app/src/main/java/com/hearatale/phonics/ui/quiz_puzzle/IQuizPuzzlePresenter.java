package com.hearatale.phonics.ui.quiz_puzzle;

import com.hearatale.phonics.data.model.phonics.letters.LetterModel;
import com.hearatale.phonics.data.model.phonics.letters.PuzzlePieceModel;
import com.hearatale.phonics.data.model.phonics.letters.WordModel;
import com.hearatale.phonics.data.model.typedef.DifficultyDef;
import com.hearatale.phonics.ui.base.activity.IPresenterMVP;

import java.util.List;

public interface IQuizPuzzlePresenter {


    void savePuzzlePiece(String displayLetter, PuzzlePieceModel puzzlePiece);

    void savePuzzlePiece(String displayLetter, List<PuzzlePieceModel> puzzlePieces);

    List<PuzzlePieceModel> getCompletedPuzzlePieces(String displayLetter);

    void savePuzzleBase64(String displayLetter, String base64);

    String getPuzzleBase64(String displayLetter);

    LetterModel randomLetter(@DifficultyDef int diffDef);

    List<WordModel> getSelectedWords();

    List<WordModel> getSelectedWords(LetterModel letter, boolean puzzleRandom);

    int getStarConsecutive(String displayLetter);

    void saveStarConsecutive(String displayLetter, int starConsecutive);

}
