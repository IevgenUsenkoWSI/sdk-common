package com.wsi.scheduler;

import android.util.Log;

import junit.framework.TestCase;

/**
 * Created by Ievgen Usenko on 7/2/14.
 */
public class SchedulerTest extends TestCase {
    private static final String TAG = SchedulerTest.class.getSimpleName();
    private Scheduler scheduler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        scheduler = new Scheduler();
    }

    @Override
    protected void tearDown() throws Exception {
        scheduler.cancelAll();
        super.tearDown();
    }

    /**
     * 1. All listeners which are not periodical and has finished, must be removed from the
     * listeners collection.
     * 2. In case of no active tasks, working thread must be terminated automatically.
     */
    public void testNotPeriodical() throws Exception {
        StateAwareSchedulerListener listener = new StateAwareSchedulerListener("single");

        scheduler.schedule(listener, 100);
        assertTrue(scheduler.mSchedulerThread.isRunning());
        assertTrue(scheduler.mSchedulerThread.isAlive());

        /* let working thread do the job */
        Thread.sleep(SchedulerThread.DEFAULT_SCHEDULER_PERIOD);
        assertTrue(listener.isOnTimerCalled());
        Thread.sleep(SchedulerThread.DEFAULT_SCHEDULER_PERIOD);

        assertEquals(0, scheduler.mListeners.size());
        assertFalse(scheduler.mSchedulerThread.isRunning());
        assertFalse(scheduler.mSchedulerThread.isAlive());
    }

    public void testPeriodical() throws Exception {
        StateAwareSchedulerListener listener = new StateAwareSchedulerListener("periodical");
        int timesToCall = 3;

        scheduler.schedule(listener, 100, true);
        assertTrue(scheduler.mSchedulerThread.isRunning());
        assertTrue(scheduler.mSchedulerThread.isAlive());

         /* let working thread do the job */
        Thread.sleep(SchedulerThread.DEFAULT_SCHEDULER_PERIOD * timesToCall);
        assertEquals(timesToCall, listener.getOnTimerCount());
        Thread.sleep(SchedulerThread.DEFAULT_SCHEDULER_PERIOD);

//        assertEquals(0, scheduler.mListeners.size());
//        assertFalse(scheduler.mSchedulerThread.isRunning());
//        assertFalse(scheduler.mSchedulerThread.isAlive());
    }

    public void testCancelOne() throws Exception {
        StateAwareSchedulerListener listener = new StateAwareSchedulerListener("toCancel");
        scheduler.schedule(listener, SchedulerThread.DEFAULT_SCHEDULER_PERIOD, true);
        assertTrue(scheduler.mSchedulerThread.isRunning());
        assertTrue(scheduler.mSchedulerThread.isAlive());

        scheduler.cancel(listener);
        Thread.sleep(SchedulerThread.DEFAULT_SCHEDULER_PERIOD * 2);

        assertFalse(listener.isOnTimerCalled());
        assertEquals(0, scheduler.mListeners.size());
        assertFalse(scheduler.mSchedulerThread.isRunning());
        assertFalse(scheduler.mSchedulerThread.isAlive());
    }

    private static class StateAwareSchedulerListener implements SchedulerListener {
        private final String tag;
        private int onTimerCount = 0;

        private StateAwareSchedulerListener(String tag) {
            this.tag = tag;
        }

        @Override
        public void onTimer() {
            onTimerCount++;
            Log.i(TAG, "onTimer: " + tag + ", count: " + onTimerCount);

        }

        public boolean isOnTimerCalled() {
            return onTimerCount > 0;
        }

        public int getOnTimerCount() {
            return onTimerCount;
        }

    }
}
