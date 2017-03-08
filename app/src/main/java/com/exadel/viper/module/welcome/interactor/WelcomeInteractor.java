package com.exadel.viper.module.welcome.interactor;

import com.exadel.viper.common.component.ViperInteractor;
import com.exadel.viper.module.welcome.entity.Message;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;

/**
 * Welcome Interactor.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomeInteractor extends ViperInteractor, WelcomeRepository.Interactor {
    
    void retrieveMessage();
    
    interface Presenter {
        
        void onRetrieve(Message message);
        
    }
}
