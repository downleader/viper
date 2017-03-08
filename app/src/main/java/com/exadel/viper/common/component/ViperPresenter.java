package com.exadel.viper.common.component;

import com.exadel.viper.common.base.ViperComponent;
import com.exadel.viper.common.state.ViperState;

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
