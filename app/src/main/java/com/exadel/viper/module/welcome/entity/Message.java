package com.exadel.viper.module.welcome.entity;

/**
 * Message.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class Message {
    
    private final String mText;
    
    public Message(String text) {
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
