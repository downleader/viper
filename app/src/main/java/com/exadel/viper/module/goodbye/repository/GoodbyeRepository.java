package com.exadel.viper.module.goodbye.repository;

import com.exadel.viper.impl.component.DefaultInteractor;
import com.exadel.viper.impl.component.DefaultRepository;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;

/**
 * Goodbye Repository.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public interface GoodbyeRepository extends DefaultRepository<GoodbyeRepository.Interactor> {
    
    void loadMessage();
    
    interface Interactor extends DefaultInteractor {
        
        void onLoad(GoodbyeMessage message);
        
    }
}
