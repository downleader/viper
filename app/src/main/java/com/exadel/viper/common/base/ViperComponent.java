package com.exadel.viper.common.base;

/**
 * Viper Component.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface ViperComponent {
    
    void onBind();
    
    void onUnbind(boolean destroy);
    
}
