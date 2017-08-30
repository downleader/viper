package com.exadel.viper.module.goodbye;

import com.exadel.viper.impl.module.DefaultModule;
import com.exadel.viper.module.goodbye.interactor.GoodbyeInteractor;
import com.exadel.viper.module.goodbye.presenter.GoodbyePresenter;
import com.exadel.viper.module.goodbye.repository.GoodbyeRepository;
import com.exadel.viper.module.goodbye.view.GoodbyeActivity;

/**
 * Goodbye Module.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeModule extends DefaultModule<GoodbyeRepository,
        GoodbyeInteractor, GoodbyePresenter, GoodbyeActivity> {
    
    public GoodbyeModule(String key,
                         GoodbyeRepository repository,
                         GoodbyeInteractor interactor,
                         GoodbyePresenter presenter,
                         GoodbyeActivity view) {
        super(key, repository, interactor, presenter, view);
    }
}
