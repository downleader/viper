package com.exadel.viper.impl.state.transformer;

import android.os.Parcelable;

import com.exadel.viper.core.state.transformer.ViperTransformer;
import com.exadel.viper.impl.state.DefaultState;

/**
 * Default Transformer.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class DefaultTransformer implements ViperTransformer<DefaultState, Parcelable> {
    
    @Override
    public Parcelable exportState(DefaultState defaultState) {
        return null;
    }
    
    @Override
    public DefaultState importState(Parcelable parcelable) {
        return null;
    }
}
