package com.exadel.viperdemo.module.welcome;

import com.exadel.viper.core.module.ViperModule;

import com.exadel.viperdemo.module.welcome.presenter.WelcomePresenter;
import com.exadel.viperdemo.module.welcome.presenter.WelcomePresenterState;
import com.exadel.viperdemo.module.welcome.repository.WelcomeRepository;
import com.exadel.viperdemo.module.welcome.repository.WelcomeRepositoryState;
import com.exadel.viperdemo.module.welcome.view.WelcomeActivity;
import com.exadel.viperdemo.module.welcome.view.WelcomeViewState;

/**
 * Welcome Module.
 *
 * @version 1.0 Apr 01 2017
 * @author  downleader
 */
public class WelcomeModule extends ViperModule<
        WelcomeRepositoryState, WelcomeRepository,
        WelcomePresenterState, WelcomePresenter,
        WelcomeViewState, WelcomeActivity> {
    
    public WelcomeModule(String key,
                         WelcomeRepository repository,
                         WelcomePresenter presenter,
                         WelcomeActivity view) {
        super(key, repository, presenter, view);
    }
}
