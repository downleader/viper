package com.exadel.viper.module.welcome.transformer;

import android.os.Parcel;
import android.os.Parcelable;

import com.exadel.viper.core.state.transformer.ViperTransformer;
import com.exadel.viper.module.welcome.view.WelcomeViewState;

/**
 * Welcome View Transformer.
 *
 * @version 1.0 Mar 31 2017
 * @author  downleader
 */
public class WelcomeViewTransformer implements ViperTransformer<WelcomeViewState, Parcelable> {
    
    @Override
    public OuterState exportState(WelcomeViewState inner) {
        return new OuterState(inner.getValue());
    }
    
    @Override
    public WelcomeViewState importState(Parcelable outer) {
        OuterState outerState = (OuterState) outer;
        return new WelcomeViewState(outerState.getValue());
    }
    
    public static class OuterState implements Parcelable {
        
        private String mValue;
        
        public OuterState(String value) {
            mValue = value;
        }
        
        public OuterState(Parcel source) {
            mValue = source.readString();
        }
        
        public String getValue() {
            return mValue;
        }
        
        @Override
        public int describeContents() {
            return 0;
        }
        
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(mValue);
        }
        
        public static final Creator<OuterState> CREATOR = new Creator<OuterState>() {
            
            @Override
            public OuterState createFromParcel(Parcel source) {
                return new OuterState(source);
            }
            
            @Override
            public OuterState[] newArray(int size) {
                return new OuterState[size];
            }
        };
    }
}
