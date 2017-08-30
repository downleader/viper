package com.exadel.viper.module.welcome.repository;

import com.exadel.viper.core.state.ViperState;

/**
 * Welcome Repository State.
 *
 * @version 1.0 Mar 09 2017
 * @author  downleader
 */
public class WelcomeRepositoryState implements ViperState {
    
    private final String mValue;
    
    public WelcomeRepositoryState(String value) {
        mValue = value;
    }
    
    public String getValue() {
        return mValue;
    }
}
