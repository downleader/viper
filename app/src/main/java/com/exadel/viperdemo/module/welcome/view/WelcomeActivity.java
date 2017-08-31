package com.exadel.viperdemo.module.welcome.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exadel.viper.core.utils.ViperUtils;

import com.exadel.viperdemo.R;
import com.exadel.viperdemo.module.welcome.WelcomeModule;
import com.exadel.viperdemo.module.welcome.entity.WelcomeMessage;
import com.exadel.viperdemo.module.welcome.presenter.WelcomePresenter;
import com.exadel.viperdemo.module.welcome.presenter.WelcomePresenterImpl;
import com.exadel.viperdemo.module.welcome.repository.WelcomeRepository;
import com.exadel.viperdemo.module.welcome.repository.WelcomeRepositoryImpl;
import com.exadel.viperdemo.module.welcome.transformer.WelcomePresenterTransformer;
import com.exadel.viperdemo.module.welcome.transformer.WelcomeRepositoryTransformer;
import com.exadel.viperdemo.module.welcome.transformer.WelcomeViewTransformer;
import com.exadel.viperdemo.ui.MainActivity;

/**
 * Welcome Activity.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomeActivity extends AppCompatActivity implements WelcomePresenter.View {
    
    private static final String LOGGING_TAG = WelcomeActivity.class.getSimpleName();
    
    private static final String PERSISTENCE_KEY = "com.exadel.viperdemo.module.welcome";
    
    private WelcomeModule mViperModule;
    
    private ProgressBar mProgressBar;
    
    private TextView mMessageView;
    
    private String mState;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupUserInterfaceComponents();
        setupViper(savedInstanceState);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mViperModule.getPresenter().onUserArrived();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mViperModule.saveState(outState);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViperModule.unbind(isFinishing());
    }
    
    @Override
    public void onBind() {
        if (mState == null) {
            mState = ViperUtils.Component.VIEW.toString();
            Log.d(LOGGING_TAG, "State created: " + mState);
        } else {
            Log.d(LOGGING_TAG, "State restored: " + mState);
        }
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        mViperModule = null;
    }
    
    @Override
    public WelcomeViewState onSaveState() {
        return new WelcomeViewState(mState);
    }
    
    @Override
    public void onRestoreState(WelcomeViewState state) {
        mState = state.getValue();
    }
    
    @Override
    public void displayProgress(boolean show) {
        mMessageView.setVisibility(show ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    
    @Override
    public void greetUser(WelcomeMessage message) {
        mMessageView.setText(message.getText());
    }
    
    @Override
    public void navigateToMain() {
        startActivity(new Intent(this, MainActivity.class));
    }
    
    public void onNavigate(View view) {
        mViperModule.getPresenter().onNavigate();
    }
    
    private void setupUserInterfaceComponents() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mMessageView = (TextView) findViewById(R.id.message);
    }
    
    private void setupViper(Bundle savedInstanceState) {
        WelcomeRepository repository = new WelcomeRepositoryImpl();
        WelcomePresenter presenter = new WelcomePresenterImpl(repository, this);
        
        mViperModule = new WelcomeModule(PERSISTENCE_KEY, repository, presenter, this);
        
        mViperModule.registerRepositoryTransformer(new WelcomeRepositoryTransformer());
        mViperModule.registerPresenterTransformer(new WelcomePresenterTransformer());
        mViperModule.registerViewTransformer(new WelcomeViewTransformer());
        
        mViperModule.restoreState(savedInstanceState);
        mViperModule.bind();
    }
}
