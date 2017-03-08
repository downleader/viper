package com.exadel.viper.module.welcome.entity;

/**
 * Welcome Message.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomeMessage {
    
    private final String mText;
    
    public WelcomeMessage(String text) {
        mText = text;
    }
    
    public String getText() {
        return mText;
    }
    
    @Override
    public String toString() {
        return mText;
    }
}
