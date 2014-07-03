package com.wsi.scheduler;

/**
 * Created by Ievgen Usenko on 7/2/14.
 */
public interface SchedulerListener {
    /**
     * All operations of this method must be asynchronous, because method is running in the
     * main scheduler thread.
     */
    void onTimer();
}
