package com.exadel.viper.module.goodbye.interactor;

import com.exadel.viper.impl.state.DefaultState;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viper.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Interactor Impl.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeInteractorImpl implements GoodbyeInteractor {
    
    private GoodbyeRepository mRepository;
    
    private GoodbyeInteractor.Presenter mPresenter;
    
    public GoodbyeInteractorImpl(GoodbyeRepository repository,
                                 GoodbyeInteractor.Presenter presenter) {
        mRepository = repository;
        mPresenter = presenter;
    }
    
    @Override
    public void onBind() {
        mRepository.registerInteractor(this);
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
    public DefaultState onSaveState() {
        return null;
    }
    
    @Override
    public void onRestoreState(DefaultState state) {
        
    }
    
    @Override
    public void retrieveMessage() {
        mRepository.loadMessage();
    }
    
    @Override
    public void onLoad(GoodbyeMessage message) {
        mPresenter.onRetrieve(message);
    }
}
