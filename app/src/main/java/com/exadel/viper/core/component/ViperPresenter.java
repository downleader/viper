package com.exadel.viper.core.component;

import com.exadel.viper.core.base.ViperComponent;
import com.exadel.viper.core.state.ViperState;

/**
 * Viper Presenter.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface ViperPresenter<State extends ViperState, Interactor extends ViperInteractor<?>>
        extends ViperComponent<State> {
    
    void setInteractor(Interactor interactor);
    
}
