package com.exadel.viper.module.goodbye.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exadel.viper.R;
import com.exadel.viper.impl.component.DefaultView;
import com.exadel.viper.impl.state.DefaultState;
import com.exadel.viper.module.goodbye.GoodbyeModule;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viper.module.goodbye.interactor.GoodbyeInteractor;
import com.exadel.viper.module.goodbye.interactor.GoodbyeInteractorImpl;
import com.exadel.viper.module.goodbye.presenter.GoodbyePresenter;
import com.exadel.viper.module.goodbye.presenter.GoodbyePresenterImpl;
import com.exadel.viper.module.goodbye.repository.GoodbyeRepository;
import com.exadel.viper.module.goodbye.repository.GoodbyeRepositoryImpl;
import com.exadel.viper.ui.MainActivity;

/**
 * Goodbye Activity.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeActivity extends AppCompatActivity implements DefaultView, GoodbyePresenter.View {
    
    private GoodbyeModule mViperModule;
    
    private ProgressBar mProgressBar;
    
    private TextView mMessageView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodbye);

        setupUi();
        setupViper();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mViperModule.getPresenter().onUserArrived();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViperModule.unbind(isFinishing());
    }
    
    @Override
    public void onBind() {
        
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        mViperModule = null;
    }
    
    @Override
    public DefaultState onSaveState() {
        return null;
    }
    
    @Override
    public void onRestoreState(DefaultState state) {
        
    }
    
    @Override
    public void displayProgress(boolean show) {
        mMessageView.setVisibility(show ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    
    @Override
    public void greetUser(GoodbyeMessage message) {
        mMessageView.setText(message.getText());
    }
    
    @Override
    public void navigateToMain() {
        startActivity(new Intent(this, MainActivity.class));
    }
    
    public void onNavigate(View view) {
        mViperModule.getPresenter().onNavigate();
    }
    
    private void setupUi() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mMessageView = (TextView) findViewById(R.id.message);
    }
    
    private void setupViper() {
        GoodbyeRepository repository = new GoodbyeRepositoryImpl();
        GoodbyePresenter presenter = new GoodbyePresenterImpl(this);
        GoodbyeInteractor interactor = new GoodbyeInteractorImpl(repository, presenter);
        presenter.setInteractor(interactor);
        
        mViperModule = new GoodbyeModule(repository, interactor, presenter, this);
        mViperModule.bind();
    }
}
