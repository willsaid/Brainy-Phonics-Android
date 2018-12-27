package com.hearatale.phonics.data.prefs;


import com.hearatale.phonics.data.model.phonics.SightWordModel;
import com.hearatale.phonics.data.model.phonics.letters.LetterModel;
import com.hearatale.phonics.data.model.phonics.letters.PieceModel;
import com.hearatale.phonics.data.model.phonics.letters.PuzzlePieceModel;
import com.hearatale.phonics.data.model.typedef.SightWordsCategoryDef;

import java.util.List;
import java.util.Map;

public interface PreferencesHelper {

    void removePref(String name);

    void clearPref();

    Map<String, List<LetterModel>> getLetters();

    void setPiecesCompletedByLetter(String letter, List<PieceModel> piecesCompleted);

    List<PieceModel> getPiecesCompletedByLetter(String letter);

    void savePuzzlePiece(String displayLetter, PuzzlePieceModel puzzlePiece);

    void savePuzzlePiece(String displayLetter, List<PuzzlePieceModel> puzzlePieces);

    List<PuzzlePieceModel> getCompletedPuzzlePieces(String displayLetter);

    void savePuzzleBase64(String displayLetter, String base64);

    String getPuzzleBase64(String displayLetter);

    List<SightWordModel> getSightWords(@SightWordsCategoryDef int category);

    void setTotalGoldCount(int count);

    int getTotalGoldCount();

    void setTotalSilverCount(int count);

    int getTotalSilverCount();

    void setSightWordStarCount(String text,int count);

    int getSightWordStarCount(String text);

    int getStarConsecutive(String displayLetter);

    void saveStarConsecutive(String displayLetter, int starConsecutive);

}
