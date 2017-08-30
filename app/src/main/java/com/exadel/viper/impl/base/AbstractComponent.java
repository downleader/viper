package com.exadel.viper.impl.base;

/**
 * Abstract Component.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public abstract class AbstractComponent extends DefaultComponent {
    
    protected boolean mBound;
    
    @Override
    public void onBind() {
        mBound = true;
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        mBound = false;
    }
    
    public boolean isBound() {
        return mBound;
    }
}
