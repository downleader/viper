package com.exadel.viper.module.goodbye.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.exadel.viper.impl.base.DefaultComponent;
import com.exadel.viper.module.goodbye.entity.GoodbyeMessage;

import java.util.Random;

/**
 * Goodbye Repository Impl.
 *
 * @version 1.0 Aug 30 2017
 * @author  downleader
 */
public class GoodbyeRepositoryImpl extends DefaultComponent implements GoodbyeRepository {
    
    private static final String LOGGING_TAG = GoodbyeRepositoryImpl.class.getSimpleName();

    private GoodbyeRepository.Interactor mInteractor;
    
    private MessageTask mMessageTask;
    
    private GoodbyeMessage mPendingMessage;
    
    @Override
    public void onUnbind(boolean shutdown) {
        super.onUnbind(shutdown);
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
    public void registerInteractor(GoodbyeRepository.Interactor interactor) {
        mInteractor = interactor;
        if (mPendingMessage != null) {
            mInteractor.onLoad(mPendingMessage);
            mPendingMessage = null;
        }
    }
    
    @Override
    public void unregisterInteractor(GoodbyeRepository.Interactor interactor) {
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
    
    private class MessageTask extends AsyncTask<Void, Void, GoodbyeMessage> {
        
        @Override
        protected GoodbyeMessage doInBackground(Void... params) {
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
                    "Goodbye!",
                    "Smell of farewell and gasoline...",
                    "Hey! See you on the other side.",
            };
            String message = messages[random.nextInt(messages.length)];
            return new GoodbyeMessage(message);
        }
        
        @Override
        protected void onPostExecute(GoodbyeMessage message) {
            clearTask();
            if (isBound()) {
                if (mInteractor != null) {
                    mInteractor.onLoad(message);
                }
            } else {
                mPendingMessage = message;
            }
        }
        
        @Override
        protected void onCancelled(GoodbyeMessage message) {
            clearTask();
        }
    }
}
