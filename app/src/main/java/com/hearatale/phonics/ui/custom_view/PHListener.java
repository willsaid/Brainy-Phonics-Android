package com.hearatale.phonics.ui.custom_view;

public interface PHListener {

    interface AnimationListener {
        void onAnimationEnd();
    }

    interface AudioListener {
        void onAudioCompleted();
    }
}
