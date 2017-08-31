package com.exadel.viper.impl.base;

import com.exadel.viper.impl.state.DefaultState;

/**
 * Default Component.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public abstract class DefaultComponent extends AbstractComponent<DefaultState> {
    
    @Override
    public DefaultState onSaveState() {
        return null;
    }
    
    @Override
    public void onRestoreState(DefaultState state) {
        
    }
}
