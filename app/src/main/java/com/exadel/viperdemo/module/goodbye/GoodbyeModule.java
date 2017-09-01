package com.exadel.viperdemo.module.goodbye;

import com.exadel.viper.impl.module.DefaultModule;

import com.exadel.viperdemo.module.goodbye.presenter.GoodbyePresenter;
import com.exadel.viperdemo.module.goodbye.repository.GoodbyeRepository;

/**
 * Goodbye Module.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeModule extends DefaultModule<GoodbyeRepository,
        GoodbyePresenter, GoodbyePresenter.View> {
    
    public GoodbyeModule(GoodbyeRepository repository,
                         GoodbyePresenter presenter,
                         GoodbyePresenter.View view) {
        super(repository, presenter, view);
    }
}
