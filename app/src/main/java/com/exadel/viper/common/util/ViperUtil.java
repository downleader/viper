package com.exadel.viper.common.util;

/**
 * Viper Util.
 *
 * @version 1.0 Apr 01 2017
 * @author  downleader
 */
public final class ViperUtil {
    
    public enum Component {
        REPOSITORY,
        INTERACTOR,
        PRESENTER,
        VIEW,
    }
    
    public static String createKey(String base, Component component) {
        return base + "." + component;
    }
}
