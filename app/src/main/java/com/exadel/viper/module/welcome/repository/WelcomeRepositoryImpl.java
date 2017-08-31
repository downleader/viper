package com.exadel.viper.module.welcome.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.exadel.viper.core.utils.ViperUtils;
import com.exadel.viper.module.welcome.entity.WelcomeMessage;

import java.util.Random;

/**
 * Welcome Repository Impl.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomeRepositoryImpl implements WelcomeRepository {
    
    private static final String LOGGING_TAG = WelcomeRepositoryImpl.class.getSimpleName();
    
    private boolean mBound;

    private WelcomeRepository.Presenter mPresenter;
    
    private MessageTask mMessageTask;
    
    private WelcomeMessage mPendingMessage;
    
    private String mState;
    
    @Override
    public void onBind() {
        mBound = true;
        if (mState == null) {
            mState = ViperUtils.Component.REPOSITORY.toString();
            Log.d(LOGGING_TAG, "State created: " + mState);
        } else {
            Log.d(LOGGING_TAG, "State restored: " + mState);
        }
    }
    
    @Override
    public void onUnbind(boolean shutdown) {
        mBound = false;
        if (shutdown) {
            if (mMessageTask != null) {
                if (!mMessageTask.cancel(true)) {
                    clearTask();
                } else {
                    Log.d(LOGGING_TAG, "Task has been canceled.");
                }
            }
        }
    }
    
    @Override
    public WelcomeRepositoryState onSaveState() {
        return new WelcomeRepositoryState(mState);
    }
    
    @Override
    public void onRestoreState(WelcomeRepositoryState state) {
        mState = state.getValue();
    }
    
    @Override
    public void registerPresenter(WelcomeRepository.Presenter presenter) {
        mPresenter = presenter;
        if (mPendingMessage != null) {
            mPresenter.onLoad(mPendingMessage);
            mPendingMessage = null;
        }
    }
    
    @Override
    public void unregisterPresenter(WelcomeRepository.Presenter presenter) {
        mPresenter = null;
    }
    
    @Override
    public void loadMessage() {
        mMessageTask = new MessageTask();
        mMessageTask.execute();
    }
    
    private void clearTask() {
        Log.d(LOGGING_TAG, "Task has been cleared.");
        mMessageTask = null;
    }
    
    private class MessageTask extends AsyncTask<Void, Void, WelcomeMessage> {
        
        @Override
        protected WelcomeMessage doInBackground(Void... params) {
            Random random = new Random(System.currentTimeMillis());
            int loadTimeInSeconds = random.nextInt(3) + 2;
            try {
                Thread.sleep(loadTimeInSeconds * 1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            if (isCancelled()) {
                return null;
            }
            String[] messages = {
                    "Welcome!",
                    "Hello hello hello how low?",
                    "Hey! I'm surprised it works.",
            };
            String message = messages[random.nextInt(messages.length)];
            return new WelcomeMessage(message);
        }
        
        @Override
        protected void onPostExecute(WelcomeMessage message) {
            clearTask();
            if (mBound) {
                if (mPresenter != null) {
                    mPresenter.onLoad(message);
                }
            } else {
                mPendingMessage = message;
            }
        }
        
        @Override
        protected void onCancelled(WelcomeMessage message) {
            clearTask();
        }
    }
}
