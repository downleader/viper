package com.exadel.viper.module.welcome.repository;

import com.exadel.viper.core.component.ViperRepository;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;

/**
 * Welcome Repository.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomeRepository extends ViperRepository<WelcomeRepositoryState, WelcomeRepository.Interactor> {
    
    void loadMessage();
    
    interface Interactor {
        
        void onLoad(WelcomeMessage message);
        
    }
}
