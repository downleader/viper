package com.exadel.viper.module.welcome;

import com.exadel.viper.core.module.ViperModule;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractor;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractorState;
import com.exadel.viper.module.welcome.presenter.WelcomePresenter;
import com.exadel.viper.module.welcome.presenter.WelcomePresenterState;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;
import com.exadel.viper.module.welcome.repository.WelcomeRepositoryState;
import com.exadel.viper.module.welcome.view.WelcomeActivity;
import com.exadel.viper.module.welcome.view.WelcomeViewState;

/**
 * Welcome Module.
 *
 * @version 1.0 Apr 01 2017
 * @author  downleader
 */
public class WelcomeModule extends ViperModule<
        WelcomeRepositoryState, WelcomeRepository,
        WelcomeInteractorState, WelcomeInteractor,
        WelcomePresenterState, WelcomePresenter,
        WelcomeViewState, WelcomeActivity> {
    
    public WelcomeModule(String key,
                         WelcomeRepository repository,
                         WelcomeInteractor interactor,
                         WelcomePresenter presenter,
                         WelcomeActivity view) {
        super(key, repository, interactor, presenter, view);
    }
}
