package com.exadel.viper.impl.base;

import com.exadel.viper.impl.component.DefaultPresenter;
import com.exadel.viper.impl.component.DefaultRepository;
import com.exadel.viper.impl.component.DefaultView;
import com.exadel.viper.impl.state.DefaultState;

/**
 * Abstract Presenter.
 *
 * @version 1.0 Aug 31 2017
 * @author  downleader
 */
public abstract class AbstractPresenter<Repository extends DefaultRepository<Presenter>,
        Presenter extends DefaultPresenter, View extends DefaultView>
        extends AbstractComponent<DefaultState> implements DefaultPresenter {
    
    protected Repository mRepository;
    
    protected View mView;
    
    public AbstractPresenter(Repository repository, View view) {
        mRepository = repository;
        mView = view;
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        super.onUnbind(destroy);
        mRepository = null;
        mView = null;
    }
}
