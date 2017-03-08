package com.exadel.viper.module.welcome.presenter;

import com.exadel.viper.common.state.ViperState;

/**
 * Welcome Presenter State.
 *
 * @version 1.0 Mar 09 2017
 * @author  downleader
 */
public class WelcomePresenterState implements ViperState {
    
    private final String mValue;
    
    public WelcomePresenterState(String value) {
        mValue = value;
    }
    
    public String getValue() {
        return mValue;
    }
}
