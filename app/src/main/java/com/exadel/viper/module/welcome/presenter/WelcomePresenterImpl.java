package com.exadel.viper.module.welcome.presenter;

import android.util.Log;

import com.exadel.viper.core.utils.ViperUtils;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractor;

/**
 * Welcome Presenter Impl.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomePresenterImpl implements WelcomePresenter {
    
    private static final String LOGGING_TAG = WelcomePresenterImpl.class.getSimpleName();
    
    private WelcomePresenter.View mView;
    
    private WelcomeInteractor mInteractor;
    
    private String mState;
    
    public WelcomePresenterImpl(WelcomePresenter.View view) {
        mView = view;
    }
    
    @Override
    public void setInteractor(WelcomeInteractor interactor) {
        mInteractor = interactor;
    }
    
    @Override
    public void onBind() {
        if (mState == null) {
            mState = ViperUtils.Component.PRESENTER.toString();
            Log.d(LOGGING_TAG, "State created: " + mState);
        } else {
            Log.d(LOGGING_TAG, "State restored: " + mState);
        }
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        if (!shutdown) {
            mView = null;
            mInteractor = null;
        }
    }
    
    @Override
    public WelcomePresenterState onSaveState() {
        return new WelcomePresenterState(mState);
    }
    
    @Override
    public void onRestoreState(WelcomePresenterState state) {
        mState = state.getValue();
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
    public void onRetrieve(WelcomeMessage message) {
        mView.displayProgress(false);
        mView.greetUser(message);
    }
}
