package com.exadel.viper.module.welcome.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.exadel.viper.common.util.ViperUtil;
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
    
    private MessageTask mMessageTask;
    
    private WelcomeMessage mPendingMessage;
    
    private WelcomeRepository.Interactor mInteractor;
    
    private String mState;
    
    @Override
    public void onBind() {
        mBound = true;
        if (mState == null) {
            mState = ViperUtil.Component.REPOSITORY.toString();
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
    public void registerInteractor(Interactor interactor) {
        mInteractor = interactor;
        if (mPendingMessage != null) {
            mInteractor.onLoad(mPendingMessage);
            mPendingMessage = null;
        }
    }
    
    @Override
    public void unregisterInteractor(Interactor interactor) {
        mInteractor = null;
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
                if (mInteractor != null) {
                    mInteractor.onLoad(message);
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
