package com.exadel.viper.module.welcome.interactor;

import com.exadel.viper.module.welcome.entity.Message;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;

/**
 * Welcome Interactor Impl.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomeInteractorImpl implements WelcomeInteractor {
    
    private WelcomeRepository mRepository;
    
    private WelcomeInteractor.Presenter mPresenter;
    
    public WelcomeInteractorImpl(WelcomeRepository repository, WelcomeInteractor.Presenter presenter) {
        mRepository = repository;
        mPresenter = presenter;
    }
    
    @Override
    public void onBind() {
        mRepository.registerInteractor(this);
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        mRepository.unregisterInteractor(this);
        if (destroy) {
            mRepository = null;
            mPresenter = null;
        }
    }
    
    @Override
    public void retrieveMessage() {
        mRepository.loadMessage();
    }
    
    @Override
    public void onLoad(Message message) {
        mPresenter.onRetrieve(message);
    }
}
