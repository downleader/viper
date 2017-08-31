package com.exadel.viper.module.welcome.presenter;

import com.exadel.viper.core.component.ViperView;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;
import com.exadel.viper.module.welcome.view.WelcomeViewState;

/**
 * Welcome Presenter.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface WelcomePresenter extends WelcomeRepository.Presenter {
    
    void onUserArrived();
    
    void onNavigate();
    
    interface View extends ViperView<WelcomeViewState> {
        
        void displayProgress(boolean show);
        
        void greetUser(WelcomeMessage message);
        
        void navigateToMain();
        
    }
}
