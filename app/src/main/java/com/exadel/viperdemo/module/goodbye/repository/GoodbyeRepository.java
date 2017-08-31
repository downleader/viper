package com.exadel.viperdemo.module.goodbye.repository;

import com.exadel.viper.impl.component.DefaultPresenter;
import com.exadel.viper.impl.component.DefaultRepository;

import com.exadel.viperdemo.module.goodbye.entity.GoodbyeMessage;

/**
 * Goodbye Repository.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public interface GoodbyeRepository extends DefaultRepository<GoodbyeRepository.Presenter> {
    
    void loadMessage();
    
    interface Presenter extends DefaultPresenter {
        
        void onLoad(GoodbyeMessage message);
        
    }
}
