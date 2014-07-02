package com.wsi.scheduler;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Ievgen Usenko on 7/2/14.
 */
class SchedulerThread extends Thread {
    private static final String TAG = SchedulerThread.class.getSimpleName();
    static final long DEFAULT_SCHEDULER_PERIOD = 1000;

    private volatile boolean mRunning = true;
    private long mSchedulerPeriod = DEFAULT_SCHEDULER_PERIOD;
    private Map<SchedulerListener, SchedulerTimerInfo> mListeners;

    SchedulerThread(Map<SchedulerListener, SchedulerTimerInfo> listeners) {
        setListeners(listeners);
    }

    @Override
    public void run() {
        while (mRunning) {
            if (mListeners == null || mListeners.isEmpty()) {
                mRunning = false;
                Log.d(TAG, "collection of task is empty. stop scheduler thread");
                return;
            }

            for (Iterator<SchedulerListener> iterator = mListeners.keySet().iterator(); iterator.hasNext(); ) {
                SchedulerListener listener = iterator.next();
                SchedulerTimerInfo timer = mListeners.get(listener);

                if (timer.cancelled) {
                    iterator.remove();
                    continue;
                }

                timer.currentTime -= mSchedulerPeriod;
                if (timer.currentTime <= 0) {
                    listener.onTimer();
                }

                if (timer.repeat) {
                    timer.currentTime = timer.period;
                } else {
                    iterator.remove();
                }
            }

            try {
                sleep(mSchedulerPeriod);
            } catch (InterruptedException e) {
                if (mRunning) Log.e(TAG, "scheduler is interrupted", e);
                mRunning = false;
                return;
            }
        }
    }

    boolean isRunning() {
        return mRunning;
    }

    void terminate() {
        mRunning = false;
        interrupt();
    }

    void setListeners(Map<SchedulerListener, SchedulerTimerInfo> listeners) {
        if (listeners == null) throw new IllegalArgumentException("listeners must not be null");
        this.mListeners = listeners;
    }
}
