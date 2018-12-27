package com.hearatale.phonics.data.model.phonics.letters;

public interface IWord {

    String getText();

    String getPronunciation();

    TimedAudioInfoModel getTimedAudioInfo();
}
