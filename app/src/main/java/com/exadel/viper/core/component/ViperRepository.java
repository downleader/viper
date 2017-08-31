package com.exadel.viper.core.component;

import com.exadel.viper.core.base.ViperComponent;
import com.exadel.viper.core.state.ViperState;

/**
 * Viper Repository.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface ViperRepository<State extends ViperState,
        Interactor extends ViperInteractor<? extends ViperState>>
        extends ViperComponent<State> {
    
    void registerInteractor(Interactor interactor);
    
    void unregisterInteractor(Interactor interactor);
    
}
