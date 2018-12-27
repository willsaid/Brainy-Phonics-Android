package com.hearatale.phonics.ui.quiz;

import com.hearatale.phonics.data.model.phonics.SectionQuiz;
import com.hearatale.phonics.data.model.phonics.letters.LetterModel;
import com.hearatale.phonics.ui.base.activity.IViewMVP;

import java.util.List;

public interface IQuiz extends IViewMVP {

    void updateDataQuiz(List<SectionQuiz> data);

    int getDifficulty();
}
