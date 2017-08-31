package com.exadel.viper.impl.component;

import com.exadel.viper.core.component.ViperRepository;
import com.exadel.viper.impl.state.DefaultState;

/**
 * Default Repository.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public interface DefaultRepository<Interactor extends DefaultInteractor>
        extends ViperRepository<DefaultState, Interactor> {
    
}
