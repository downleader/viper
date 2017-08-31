package com.exadel.viper.module.welcome.interactor;

import com.exadel.viper.core.component.ViperPresenter;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;
import com.exadel.viper.module.welcome.presenter.WelcomePresenterState;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;

/**
 * Welcome Interactor.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomeInteractor extends WelcomeRepository.Interactor {
    
    void retrieveMessage();
    
    interface Presenter extends ViperPresenter<WelcomePresenterState, WelcomeInteractor> {
        
        void onRetrieve(WelcomeMessage message);
        
    }
}
