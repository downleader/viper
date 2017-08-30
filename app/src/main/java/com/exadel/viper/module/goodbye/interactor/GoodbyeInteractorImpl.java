package com.exadel.viper.module.goodbye.interactor;

import com.exadel.viper.impl.base.AbstractComponent;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viper.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Interactor Impl.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeInteractorImpl extends AbstractComponent implements GoodbyeInteractor {
    
    private GoodbyeRepository mRepository;
    
    private GoodbyeInteractor.Presenter mPresenter;
    
    public GoodbyeInteractorImpl(GoodbyeRepository repository,
                                 GoodbyeInteractor.Presenter presenter) {
        mRepository = repository;
        mPresenter = presenter;
    }
    
    @Override
    public void onBind() {
        super.onBind();
        mRepository.registerInteractor(this);
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        super.onUnbind(shutdown);
        mRepository.unregisterInteractor(this);
        if (!shutdown) {
            mRepository = null;
            mPresenter = null;
        }
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
