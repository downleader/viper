package com.exadel.viper.module.welcome.interactor;

import android.util.Log;

import com.exadel.viper.common.util.ViperUtil;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;

/**
 * Welcome Interactor Impl.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomeInteractorImpl implements WelcomeInteractor {
    
    private static final String LOGGING_TAG = WelcomeInteractorImpl.class.getSimpleName();
    
    private WelcomeRepository mRepository;
    
    private WelcomeInteractor.Presenter mPresenter;
    
    private String mState;
    
    public WelcomeInteractorImpl(WelcomeRepository repository, WelcomeInteractor.Presenter presenter) {
        mRepository = repository;
        mPresenter = presenter;
    }
    
    @Override
    public void onBind() {
        mRepository.registerInteractor(this);
        if (mState == null) {
            mState = ViperUtil.Component.INTERACTOR.toString();
            Log.d(LOGGING_TAG, "State created: " + mState);
        } else {
            Log.d(LOGGING_TAG, "State restored: " + mState);
        }
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        mRepository.unregisterInteractor(this);
        if (!shutdown) {
            mRepository = null;
            mPresenter = null;
        }
    }
    
    @Override
    public WelcomeInteractorState onSaveState() {
        return new WelcomeInteractorState(mState);
    }
    
    @Override
    public void onRestoreState(WelcomeInteractorState state) {
        mState = state.getValue();
    }
    
    @Override
    public void retrieveMessage() {
        mRepository.loadMessage();
    }
    
    @Override
    public void onLoad(WelcomeMessage message) {
        mPresenter.onRetrieve(message);
    }
}
