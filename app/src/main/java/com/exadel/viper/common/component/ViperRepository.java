package com.exadel.viper.common.component;

import com.exadel.viper.common.base.ViperComponent;
import com.exadel.viper.common.state.ViperState;

/**
 * Viper Repository.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface ViperRepository<State extends ViperState, Interactor> extends ViperComponent<State> {
    
    void registerInteractor(Interactor interactor);
    
    void unregisterInteractor(Interactor interactor);
    
}
