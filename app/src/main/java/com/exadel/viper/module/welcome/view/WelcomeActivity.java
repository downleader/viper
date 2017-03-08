package com.exadel.viper.module.welcome.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exadel.viper.R;
import com.exadel.viper.common.Viper;
import com.exadel.viper.common.module.ViperModule;
import com.exadel.viper.module.welcome.entity.Message;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractor;
import com.exadel.viper.module.welcome.interactor.WelcomeInteractorImpl;
import com.exadel.viper.module.welcome.presenter.WelcomePresenter;
import com.exadel.viper.module.welcome.presenter.WelcomePresenterImpl;
import com.exadel.viper.module.welcome.repository.WelcomeRepository;
import com.exadel.viper.module.welcome.repository.WelcomeRepositoryImpl;

/**
 * Welcome Activity.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomeActivity extends AppCompatActivity implements WelcomePresenter.View {
    
    private ViperModule<WelcomeRepository, WelcomeInteractor, WelcomePresenter> mViperModule;
    
    private ProgressBar mProgressBar;
    
    private TextView mMessageView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mMessageView = (TextView) findViewById(R.id.message);

        WelcomePresenter presenter = new WelcomePresenterImpl(this);
        WelcomeRepository repository = new WelcomeRepositoryImpl();
        WelcomeInteractor interactor = new WelcomeInteractorImpl(repository, presenter);
        
        mViperModule = Viper.assemble(repository, interactor, presenter);
        mViperModule.onBind();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mViperModule.getPresenter().onUserArrived();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        mViperModule.onUnbind(!isFinishing());
        mViperModule = null;
    }
    
    @Override
    public void displayProgress(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    
    @Override
    public void greetUser(Message message) {
        mMessageView.setText(message.getText());
    }
}
