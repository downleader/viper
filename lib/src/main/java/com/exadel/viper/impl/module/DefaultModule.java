package com.exadel.viper.impl.module;

import com.exadel.viper.core.module.ViperModule;
import com.exadel.viper.impl.component.DefaultPresenter;
import com.exadel.viper.impl.component.DefaultRepository;
import com.exadel.viper.impl.component.DefaultView;
import com.exadel.viper.impl.state.DefaultState;

/**
 * Default Module.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class DefaultModule
        <Repository extends DefaultRepository<?>,
        Presenter extends DefaultPresenter,
        View extends DefaultView>
        
        extends ViperModule
            <DefaultState, Repository,
            DefaultState, Presenter,
            DefaultState, View> {
    
    public DefaultModule(Repository repository,
                         Presenter presenter,
                         View view) {
        super(null, repository, presenter, view);
    }
}
