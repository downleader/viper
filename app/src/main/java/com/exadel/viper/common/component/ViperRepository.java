package com.exadel.viper.common.component;

import com.exadel.viper.common.base.ViperComponent;

/**
 * Viper Repository.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface ViperRepository<Interactor> extends ViperComponent {
    
    void registerInteractor(Interactor interactor);
    
    void unregisterInteractor(Interactor interactor);
    
}
