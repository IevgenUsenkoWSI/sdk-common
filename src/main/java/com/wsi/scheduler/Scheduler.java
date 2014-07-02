package com.wsi.scheduler;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Ievgen Usenko on 7/2/14.
 */
public class Scheduler {
    private static final String TAG = Scheduler.class.getSimpleName();

    Map<SchedulerListener, SchedulerTimerInfo> mListeners = Collections.EMPTY_MAP;
    SchedulerThread mSchedulerThread;

    public synchronized void schedule(final SchedulerListener listener,
                                      final long delay,
                                      final boolean repeat) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        if (delay <= 0) {
            throw new IllegalArgumentException("invalid delay value " + delay);
        }

        mListeners = new WeakHashMap<SchedulerListener, SchedulerTimerInfo>(mListeners);
        mListeners.put(listener, new SchedulerTimerInfo(delay, repeat));

        if (mSchedulerThread == null || !mSchedulerThread.isRunning()) {
            mSchedulerThread = new SchedulerThread(mListeners);
            mSchedulerThread.start();
        } else {
            mSchedulerThread.setListeners(mListeners);
        }
    }

    public synchronized void schedule(final SchedulerListener listener, final int delay) {
        schedule(listener, delay, false);
    }

    public synchronized void cancel(final SchedulerListener listener) {
        SchedulerTimerInfo info = mListeners.get(listener);
        if (info == null) return;

        info.cancelled = true;
    }

    public synchronized void cancelAll() {
        if (mSchedulerThread == null || !mSchedulerThread.isRunning()) return;
        mSchedulerThread.terminate();
    }
}
