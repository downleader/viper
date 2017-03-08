package com.exadel.viper.module.welcome.repository;

import com.exadel.viper.common.component.ViperRepository;
import com.exadel.viper.module.welcome.entity.Message;

/**
 * Welcome Repository.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomeRepository extends ViperRepository<WelcomeRepository.Interactor> {
    
    void loadMessage();
    
    interface Interactor {
        
        void onLoad(Message message);
        
    }
}
