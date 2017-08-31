package com.exadel.viper.module.goodbye.interactor;

import com.exadel.viper.impl.component.DefaultPresenter;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viper.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Interactor.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public interface GoodbyeInteractor extends GoodbyeRepository.Interactor {
    
    void retrieveMessage();
    
    interface Presenter extends DefaultPresenter<GoodbyeInteractor> {
        
        void onRetrieve(GoodbyeMessage message);
        
    }
}
