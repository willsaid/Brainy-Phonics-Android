package com.hearatale.phonics.ui.base.activity;

public interface IPresenterMVP<V extends IViewMVP> {

    void attachView(V viewMVP);

    void detachView();
}
