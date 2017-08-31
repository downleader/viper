package com.exadel.viper.core.utils;

/**
 * Viper Utils.
 *
 * @version 1.0 Apr 01 2017
 * @author  downleader
 */
public final class ViperUtils {
    
    public static final String KEY_SEPARATOR = ".";
    
    public enum Component {
        REPOSITORY,
        PRESENTER,
        VIEW,
    }
    
    public static String createKey(String base, Component component) {
        return base + KEY_SEPARATOR + component;
    }
}
