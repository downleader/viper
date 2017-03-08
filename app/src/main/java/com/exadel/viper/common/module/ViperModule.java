package com.exadel.viper.common.module;

import com.exadel.viper.common.base.ViperComponent;
import com.exadel.viper.common.component.ViperInteractor;
import com.exadel.viper.common.component.ViperPresenter;
import com.exadel.viper.common.component.ViperRepository;

/**
 * Viper Module.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class ViperModule<Repository extends ViperRepository<?>, Interactor extends ViperInteractor,
        Presenter extends ViperPresenter<Interactor>> implements ViperComponent {
    
    private Repository mRepository;
    
    private Interactor mInteractor;
    
    private Presenter mPresenter;
    
    public ViperModule(Repository repository, Interactor interactor, Presenter presenter) {
        mRepository = repository;
        mInteractor = interactor;
        mPresenter = presenter;
    }
    
    public Repository getRepository() {
        return mRepository;
    }
    
    public Interactor getInteractor() {
        return mInteractor;
    }
    
    public Presenter getPresenter() {
        return mPresenter;
    }
    
    @Override
    public void onBind() {
        mRepository.onBind();
        mInteractor.onBind();
        mPresenter.onBind();
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        mRepository.onUnbind(destroy);
        mInteractor.onUnbind(true);
        mPresenter.onUnbind(true);
    }
}
