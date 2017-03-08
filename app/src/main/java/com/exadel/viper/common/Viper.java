package com.exadel.viper.common;

import com.exadel.viper.common.component.ViperInteractor;
import com.exadel.viper.common.component.ViperPresenter;
import com.exadel.viper.common.component.ViperRepository;
import com.exadel.viper.common.module.ViperModule;

/**
 * Viper.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public final class Viper {
    
    public static <R extends ViperRepository<?>, I extends ViperInteractor, P extends ViperPresenter<I>>
    ViperModule<R, I, P> assemble(R repository, I interactor, P presenter) {
        presenter.setInteractor(interactor);
        return new ViperModule<>(repository, interactor, presenter);
    }
}
