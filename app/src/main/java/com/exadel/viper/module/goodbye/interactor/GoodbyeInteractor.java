package com.exadel.viper.module.goodbye.interactor;

import com.exadel.viper.impl.component.DefaultInteractor;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viper.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Interactor.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public interface GoodbyeInteractor extends DefaultInteractor, GoodbyeRepository.Interactor {
    
    void retrieveMessage();
    
    interface Presenter {
        
        void onRetrieve(GoodbyeMessage message);
        
    }
}
