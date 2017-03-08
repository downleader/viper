package com.exadel.viper.common.base;

import com.exadel.viper.common.state.ViperState;

/**
 * Viper Component.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface ViperComponent<State extends ViperState> {
    
    void onBind();
    
    void onUnbind(boolean shutdown);
    
    State onSaveState();
    
    void onRestoreState(State state);
    
}
