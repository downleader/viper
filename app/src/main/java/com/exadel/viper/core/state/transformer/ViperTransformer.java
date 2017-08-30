package com.exadel.viper.core.state.transformer;

import com.exadel.viper.core.state.ViperState;

/**
 * Viper Transformer.
 *
 * @version 1.0 Mar 31 2017
 * @author  downleader
 */
public interface ViperTransformer<Inner extends ViperState, Outer> {
    
    Outer exportState(Inner inner);
    
    Inner importState(Outer outer);
    
}
