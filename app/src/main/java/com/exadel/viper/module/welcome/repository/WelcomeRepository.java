package com.exadel.viper.module.welcome.repository;

import com.exadel.viper.core.component.ViperInteractor;
import com.exadel.viper.core.component.ViperRepository;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractorState;

/**
 * Welcome Repository.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomeRepository extends ViperRepository<WelcomeRepositoryState, WelcomeRepository.Interactor> {
    
    void loadMessage();
    
    interface Interactor extends ViperInteractor<WelcomeInteractorState> {
        
        void onLoad(WelcomeMessage message);
        
    }
}
