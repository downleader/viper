package com.exadel.viperdemo.module.goodbye.presenter;

import com.exadel.viper.impl.base.AbstractPresenter;

import com.exadel.viperdemo.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viperdemo.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Presenter Impl.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyePresenterImpl extends AbstractPresenter<GoodbyeRepository,
        GoodbyeRepository.Presenter, GoodbyePresenter.View> implements GoodbyePresenter {
    
    public GoodbyePresenterImpl(GoodbyeRepository repository, GoodbyePresenter.View view) {
        super(repository, view);
    }
    
    @Override
    public void onBind() {
        super.onBind();
        mRepository.registerPresenter(this);
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        mRepository.unregisterPresenter(this);
        super.onUnbind(destroy);
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
