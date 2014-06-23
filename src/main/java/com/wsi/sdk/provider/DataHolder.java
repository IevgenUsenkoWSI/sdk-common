package com.wsi.sdk.provider;

import android.content.Context;

import com.wsi.sdk.sync.SyncStatus;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public class DataHolder<T> {
    List<T> data = Collections.EMPTY_LIST;
    long syncTime;
    SyncStatus status = SyncStatus.UNDEFINED;

    public void setData(final List<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("collection must not be null");
        }
        this.data = data;
        status = SyncStatus.SYNCHRONIZED;
        syncTime = System.currentTimeMillis();
    }

    public void setStatus(final SyncStatus stats) {
        if (stats == null) {
            throw new IllegalArgumentException("status must not be nll");
        }
        this.status = stats;
    }

    public List<T> getData() {
        return data;
    }

    public SyncStatus getStatus() {
        return status;
    }

    public long getSyncTime() {
        return syncTime;
    }
}
