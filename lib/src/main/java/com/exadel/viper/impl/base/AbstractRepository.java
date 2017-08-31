package com.exadel.viper.impl.base;

import com.exadel.viper.impl.component.DefaultPresenter;
import com.exadel.viper.impl.component.DefaultRepository;
import com.exadel.viper.impl.state.DefaultState;

/**
 * Abstract Repository.
 *
 * @version 1.0 Aug 31 2017
 * @author  downleader
 */
public abstract class AbstractRepository<Presenter extends DefaultPresenter>
        extends AbstractComponent<DefaultState> implements DefaultRepository<Presenter> {
    
    protected Presenter mPresenter;
    
    @Override
    public void registerPresenter(Presenter presenter) {
        mPresenter = presenter;
    }
    
    @Override
    public void unregisterPresenter(Presenter presenter) {
        mPresenter = null;
    }
}
