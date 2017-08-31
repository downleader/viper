package com.exadel.viperdemo.module.welcome.repository;

import com.exadel.viper.core.component.ViperPresenter;
import com.exadel.viper.core.component.ViperRepository;

import com.exadel.viperdemo.module.welcome.entity.WelcomeMessage;
import com.exadel.viperdemo.module.welcome.presenter.WelcomePresenterState;

/**
 * Welcome Repository.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomeRepository extends ViperRepository<WelcomeRepositoryState, WelcomeRepository.Presenter> {
    
    void loadMessage();
    
    interface Presenter extends ViperPresenter<WelcomePresenterState> {
        
        void onLoad(WelcomeMessage message);
        
    }
}
