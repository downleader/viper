package com.exadel.viper.module.welcome.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.exadel.viper.module.welcome.entity.Message;

import java.util.Random;

/**
 * Welcome Repository Impl.
 *
 * @version 1.0 Mar 08 2017
 * @author  downleader
 */
public class WelcomeRepositoryImpl implements WelcomeRepository {
    
    private boolean mBound;
    
    private MessageTask mMessageTask;
    
    private Message mPendingMessage;
    
    private WelcomeRepository.Interactor mInteractor;
    
    @Override
    public void onBind() {
        mBound = true;
    }
    
    @Override
    public void onUnbind(boolean destroy) {
        mBound = false;
        if (destroy) {
            if (mMessageTask != null) {
                if (!mMessageTask.cancel(true)) {
                    clearTask();
                } else {
                    Log.d("VIPER", "Message task has been canceled.");
                }
            }
        }
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
        Log.d("VIPER", "Message task has been cleared.");
        mMessageTask = null;
    }
    
    private class MessageTask extends AsyncTask<Void, Void, Message> {
        
        @Override
        protected Message doInBackground(Void... params) {
            Random random = new Random(System.currentTimeMillis());
            int loadTimeInSeconds = random.nextInt(3) + 5;
            try {
                Thread.sleep(loadTimeInSeconds * 1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            if (isCancelled()) {
                return null;
            }
            return new Message("Hey! I'm surprised it works.");
        }
        
        @Override
        protected void onPostExecute(Message message) {
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
        protected void onCancelled(Message message) {
            clearTask();
        }
    }
}
