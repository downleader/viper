package com.exadel.viperdemo.module.welcome.transformer;

import android.os.Parcel;
import android.os.Parcelable;

import com.exadel.viper.core.state.transformer.ViperTransformer;

import com.exadel.viperdemo.module.welcome.presenter.WelcomePresenterState;

/**
 * Welcome Presenter Transformer.
 *
 * @version 1.0 Apr 01 2017
 * @author  downleader
 */
public class WelcomePresenterTransformer implements ViperTransformer<WelcomePresenterState, Parcelable> {
    
    @Override
    public OuterState exportState(WelcomePresenterState inner) {
        return new OuterState(inner.getValue());
    }
    
    @Override
    public WelcomePresenterState importState(Parcelable outer) {
        OuterState outerState = OuterState.class.cast(outer);
        return new WelcomePresenterState(outerState.getValue());
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
