package com.exadel.viper.module.welcome.presenter;

import com.exadel.viper.module.welcome.entity.Message;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractor;

/**
 * Welcome Presenter Impl.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomePresenterImpl implements WelcomePresenter {
    
    private WelcomePresenter.View mView;
    
    private WelcomeInteractor mInteractor;
    
    public WelcomePresenterImpl(WelcomePresenter.View view) {
        mView = view;
    }
    
    @Override
    public void setInteractor(WelcomeInteractor interactor) {
        mInteractor = interactor;
    }
    
    @Override
    public void onBind() {
        
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        if (destroy) {
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
    public void onRetrieve(Message message) {
        mView.displayProgress(false);
        mView.greetUser(message);
    }
}
