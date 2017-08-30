package com.exadel.viper.module.welcome.interactor;

import com.exadel.viper.core.state.ViperState;

/**
 * Welcome Interactor State.
 *
 * @version 1.0 Mar 09 2017
 * @author  downleader
 */
public class WelcomeInteractorState implements ViperState {
    
    private final String mValue;
    
    public WelcomeInteractorState(String value) {
        mValue = value;
    }
    
    public String getValue() {
        return mValue;
    }
}
