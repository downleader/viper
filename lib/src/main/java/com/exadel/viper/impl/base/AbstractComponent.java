package com.exadel.viper.impl.base;

import com.exadel.viper.core.base.ViperComponent;
import com.exadel.viper.core.state.ViperState;

/**
 * Abstract Component.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public abstract class AbstractComponent<State extends ViperState> implements ViperComponent<State> {
    
    protected boolean mBound;
    
    @Override
    public void onBind() {
        mBound = true;
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        mBound = false;
    }
    
    public boolean isBound() {
        return mBound;
    }
}
