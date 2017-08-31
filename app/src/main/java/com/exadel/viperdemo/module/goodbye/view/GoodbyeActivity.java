package com.exadel.viperdemo.module.goodbye.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exadel.viper.impl.state.DefaultState;

import com.exadel.viperdemo.R;
import com.exadel.viperdemo.module.goodbye.GoodbyeModule;
import com.exadel.viperdemo.module.goodbye.entity.GoodbyeMessage;
import com.exadel.viperdemo.module.goodbye.presenter.GoodbyePresenter;
import com.exadel.viperdemo.module.goodbye.presenter.GoodbyePresenterImpl;
import com.exadel.viperdemo.module.goodbye.repository.GoodbyeRepository;
import com.exadel.viperdemo.module.goodbye.repository.GoodbyeRepositoryImpl;

/**
 * Goodbye Activity.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeActivity extends AppCompatActivity implements GoodbyePresenter.View {
    
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
        finish();
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
        GoodbyePresenter presenter = new GoodbyePresenterImpl(repository, this);
        
        mViperModule = new GoodbyeModule(repository, presenter, this);
        mViperModule.bind();
    }
}
