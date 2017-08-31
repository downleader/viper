package com.exadel.viper.module.goodbye.presenter;

import com.exadel.viper.impl.base.DefaultComponent;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viper.module.goodbye.interactor.GoodbyeInteractor;

/**
 * Goodbye Presenter Impl.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyePresenterImpl extends DefaultComponent implements GoodbyePresenter {
    
    private GoodbyeInteractor mInteractor;
    
    private GoodbyePresenter.View mView;
    
    public GoodbyePresenterImpl(GoodbyePresenter.View view) {
        mView = view;
    }
    
    @Override
    public void setInteractor(GoodbyeInteractor interactor) {
        mInteractor = interactor;
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        super.onUnbind(shutdown);
        if (!shutdown) {
            mView = null;
            mInteractor = null;
        }
    }
    
    @Override
    public void onUserArrived() {
        mView.displayProgress(true);
        mInteractor.retrieveMessage();
    }
    
    @Override
    public void onNavigate() {
        mView.navigateToMain();
    }
    
    @Override
    public void onRetrieve(GoodbyeMessage message) {
        mView.displayProgress(false);
        mView.greetUser(message);
    }
}
