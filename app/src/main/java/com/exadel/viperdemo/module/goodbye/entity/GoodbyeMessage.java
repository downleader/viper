package com.exadel.viperdemo.module.goodbye.entity;

/**
 * Goodbye Message.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeMessage {
    
    private final String mText;
    
    public GoodbyeMessage(String text) {
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
