package com.exadel.viper.module.welcome.presenter;

import android.util.Log;

import com.exadel.viper.core.utils.ViperUtils;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;

/**
 * Welcome Presenter Impl.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomePresenterImpl implements WelcomePresenter {
    
    private static final String LOGGING_TAG = WelcomePresenterImpl.class.getSimpleName();
    
    private WelcomeRepository mRepository;
    
    private WelcomePresenter.View mView;
    
    private String mState;
    
    public WelcomePresenterImpl(WelcomeRepository repository, WelcomePresenter.View view) {
        mRepository = repository;
        mView = view;
    }
    
    @Override
    public void onBind() {
        mRepository.registerPresenter(this);
        if (mState == null) {
            mState = ViperUtils.Component.PRESENTER.toString();
            Log.d(LOGGING_TAG, "State created: " + mState);
        } else {
            Log.d(LOGGING_TAG, "State restored: " + mState);
        }
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        mRepository.unregisterPresenter(this);
        if (!shutdown) {
            mRepository = null;
            mView = null;
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
        mRepository.loadMessage();
    }
    
    @Override
    public void onNavigate() {
        mView.navigateToMain();
    }
    
    @Override
    public void onLoad(WelcomeMessage message) {
        mView.displayProgress(false);
        mView.greetUser(message);
    }
}
