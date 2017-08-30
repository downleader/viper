package com.exadel.viper.impl.base;

import com.exadel.viper.core.base.ViperComponent;
import com.exadel.viper.impl.state.DefaultState;

/**
 * Default Component.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public abstract class DefaultComponent implements ViperComponent<DefaultState> {
    
    @Override
    public DefaultState onSaveState() {
        return null;
    }
    
    @Override
    public void onRestoreState(DefaultState state) {
        
    }
}
