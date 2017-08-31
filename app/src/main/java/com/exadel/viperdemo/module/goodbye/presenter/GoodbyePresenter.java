package com.exadel.viperdemo.module.goodbye.presenter;

import com.exadel.viper.impl.component.DefaultView;

import com.exadel.viperdemo.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viperdemo.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Presenter.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public interface GoodbyePresenter extends GoodbyeRepository.Presenter {
    
    void onUserArrived();
    
    void onNavigate();
    
    interface View extends DefaultView {
        
        void displayProgress(boolean show);
        
        void greetUser(GoodbyeMessage message);
        
        void navigateToMain();
        
    }
}
