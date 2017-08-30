package com.exadel.viper.module.goodbye.presenter;

import com.exadel.viper.impl.component.DefaultPresenter;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viper.module.goodbye.interactor.GoodbyeInteractor;

/**
 * Goodbye Presenter.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public interface GoodbyePresenter extends DefaultPresenter<GoodbyeInteractor>,
        GoodbyeInteractor.Presenter {
    
    void onUserArrived();
    
    void onNavigate();
    
    interface View {
        
        void displayProgress(boolean show);
        
        void greetUser(GoodbyeMessage message);
        
        void navigateToMain();
        
    }
}
