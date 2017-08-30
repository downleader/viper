package com.exadel.viper.core.module;

import android.os.Bundle;
import android.os.Parcelable;

import com.exadel.viper.core.component.ViperInteractor;
import com.exadel.viper.core.component.ViperPresenter;
import com.exadel.viper.core.component.ViperRepository;
import com.exadel.viper.core.component.ViperView;
import com.exadel.viper.core.state.ViperState;
import com.exadel.viper.core.state.transformer.ViperTransformer;
import com.exadel.viper.core.utils.ViperUtils;

/**
 * Viper Module.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class ViperModule<RepositoryState extends ViperState,
        Repository extends ViperRepository<RepositoryState, ?>,
        InteractorState extends ViperState, Interactor extends ViperInteractor<InteractorState>,
        PresenterState extends ViperState, Presenter extends ViperPresenter<PresenterState, Interactor>,
        ViewState extends ViperState, View extends ViperView<ViewState>> {
    
    protected String mKey;

    protected Repository mRepository;

    protected Interactor mInteractor;

    protected Presenter mPresenter;

    protected View mView;

    protected ViperTransformer<RepositoryState, Parcelable> mRepositoryTransformer;

    protected ViperTransformer<InteractorState, Parcelable> mInteractorTransformer;

    protected ViperTransformer<PresenterState, Parcelable> mPresenterTransformer;

    protected ViperTransformer<ViewState, Parcelable> mViewTransformer;
    
    public ViperModule(String key,
                       Repository repository,
                       Interactor interactor,
                       Presenter presenter,
                       View view) {
        mKey = key;
        mRepository = repository;
        mInteractor = interactor;
        mPresenter = presenter;
        mView = view;
    }
    
    public Repository getRepository() {
        return mRepository;
    }
    
    public Interactor getInteractor() {
        return mInteractor;
    }
    
    public Presenter getPresenter() {
        return mPresenter;
    }
    
    public View getView() {
        return mView;
    }
    
    public void registerRepositoryTransformer(ViperTransformer<RepositoryState, Parcelable> repositoryTransformer) {
        mRepositoryTransformer = repositoryTransformer;
    }
    
    public void registerInteractorTransformer(ViperTransformer<InteractorState, Parcelable> interactorTransformer) {
        mInteractorTransformer = interactorTransformer;
    }
    
    public void registerPresenterTransformer(ViperTransformer<PresenterState, Parcelable> presenterTransformer) {
        mPresenterTransformer = presenterTransformer;
    }
    
    public void registerViewTransformer(ViperTransformer<ViewState, Parcelable> viewTransformer) {
        mViewTransformer = viewTransformer;
    }
    
    public void bind() {
        mRepository.onBind();
        mInteractor.onBind();
        mPresenter.onBind();
        mView.onBind();
    }
    
    public void unbind(boolean destroy) {
        mRepository.onUnbind(destroy);
        mInteractor.onUnbind(true);
        mPresenter.onUnbind(true);
        mView.onUnbind(true);
        
        mRepository = null;
        mInteractor = null;
        mPresenter = null;
        mView = null;
    }
    
    public void saveState(Bundle bundle) {
        if (mRepositoryTransformer != null) {
            bundle.putParcelable(
                    ViperUtils.createKey(mKey, ViperUtils.Component.REPOSITORY),
                    mRepositoryTransformer.exportState(mRepository.onSaveState()));
        }
        if (mInteractorTransformer != null) {
            bundle.putParcelable(
                    ViperUtils.createKey(mKey, ViperUtils.Component.INTERACTOR),
                    mInteractorTransformer.exportState(mInteractor.onSaveState()));
        }
        if (mPresenterTransformer != null) {
            bundle.putParcelable(
                    ViperUtils.createKey(mKey, ViperUtils.Component.PRESENTER),
                    mPresenterTransformer.exportState(mPresenter.onSaveState()));
        }
        if (mViewTransformer != null) {
            bundle.putParcelable(
                    ViperUtils.createKey(mKey, ViperUtils.Component.VIEW),
                    mViewTransformer.exportState(mView.onSaveState()));
        }
    }
    
    public void restoreState(Bundle bundle) {
        if (bundle != null) {
            if (mRepositoryTransformer != null) {
                mRepository.onRestoreState(mRepositoryTransformer.importState(bundle.getParcelable(
                        ViperUtils.createKey(mKey, ViperUtils.Component.REPOSITORY))));
            }
            if (mInteractorTransformer != null) {
                mInteractor.onRestoreState(mInteractorTransformer.importState(bundle.getParcelable(
                        ViperUtils.createKey(mKey, ViperUtils.Component.INTERACTOR))));
            }
            if (mPresenterTransformer != null) {
                mPresenter.onRestoreState(mPresenterTransformer.importState(bundle.getParcelable(
                        ViperUtils.createKey(mKey, ViperUtils.Component.PRESENTER))));
            }
            if (mViewTransformer != null) {
                mView.onRestoreState(mViewTransformer.importState(bundle.getParcelable(
                        ViperUtils.createKey(mKey, ViperUtils.Component.VIEW))));
            }
        }
    }
}
