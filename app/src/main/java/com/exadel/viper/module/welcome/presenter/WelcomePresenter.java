package com.exadel.viper.module.welcome.presenter;

import com.exadel.viper.common.component.ViperPresenter;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractor;

/**
 * Welcome Presenter.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomePresenter extends ViperPresenter<WelcomePresenterState, WelcomeInteractor>,
        WelcomeInteractor.Presenter {
    
    void onUserArrived();
    
    void onNavigate();
    
    interface View {
        
        void displayProgress(boolean show);
        
        void greetUser(WelcomeMessage message);
        
        void navigateToMain();
        
    }
}
