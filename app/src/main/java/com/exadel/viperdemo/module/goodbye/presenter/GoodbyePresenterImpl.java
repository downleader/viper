package com.exadel.viperdemo.module.goodbye.presenter;

import com.exadel.viper.impl.base.DefaultComponent;

import com.exadel.viperdemo.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viperdemo.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Presenter Impl.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyePresenterImpl extends DefaultComponent implements GoodbyePresenter {
    
    private GoodbyeRepository mRepository;
    
    private GoodbyePresenter.View mView;
    
    public GoodbyePresenterImpl(GoodbyeRepository repository,
                                GoodbyePresenter.View view) {
        mRepository = repository;
        mView = view;
    }
    
    @Override
    public void onBind() {
        super.onBind();
        mRepository.registerPresenter(this);
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        super.onUnbind(shutdown);
        mRepository.unregisterPresenter(this);
        if (!shutdown) {
            mRepository = null;
            mView = null;
        }
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
    public void onLoad(GoodbyeMessage message) {
        mView.displayProgress(false);
        mView.greetUser(message);
    }
}
