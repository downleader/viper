package com.exadel.viper.module.welcome.view;

import com.exadel.viper.core.state.ViperState;

/**
 * Welcome View State.
 *
 * @version 1.0 Mar 09 2017
 * @author  downleader
 */
public class WelcomeViewState implements ViperState {
    
    private final String mValue;
    
    public WelcomeViewState(String value) {
        mValue = value;
    }
    
    public String getValue() {
        return mValue;
    }
}
