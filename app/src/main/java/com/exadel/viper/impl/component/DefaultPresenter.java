package com.exadel.viper.impl.component;

import com.exadel.viper.core.component.ViperPresenter;
import com.exadel.viper.impl.state.DefaultState;

/**
 * Default Presenter.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public abstract class DefaultPresenter<Interactor extends DefaultInteractor>
        implements ViperPresenter<DefaultState, Interactor> {
    
}
