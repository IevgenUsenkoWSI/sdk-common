package com.wsi.scheduler;

/**
 * Created by Ievgen Usenko on 7/2/14.
 */
class SchedulerTimerInfo {
    final long period;
    final boolean repeat;
    volatile long currentTime;
    volatile boolean cancelled = false;

    SchedulerTimerInfo(int period, boolean repeat) {
        this.period = period;
        this.repeat = repeat;
        this.currentTime = period;
    }
}
