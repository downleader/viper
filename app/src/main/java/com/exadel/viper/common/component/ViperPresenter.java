package com.exadel.viper.common.component;

import com.exadel.viper.common.base.ViperComponent;

/**
 * Viper Presenter.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public interface ViperPresenter<Interactor extends ViperInteractor> extends ViperComponent {
    
    void setInteractor(Interactor interactor);
    
}
