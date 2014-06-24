package com.wsi.sdk.provider;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
abstract class AbstractSyncTask<T> {
    private static final String TAG = AbstractSyncTask.class.getSimpleName();
    private static final String DEFAULT_BASE_URL = "http://base-backend-url-stub";

    private final Context mContext;
    private final DataHolder<T> mDataHolder;

    protected AbstractSyncTask(Context mContext, DataHolder<T> mDataHolder) {
        this.mContext = mContext;
        this.mDataHolder = mDataHolder;
    }

    protected String getBaseUrl() {
        return DEFAULT_BASE_URL;
    }

    protected abstract String getUrl();

    protected abstract String getId();

    SyncStatus perform() {
        try {
            Log.d(TAG, "sync. start " + getId());
            mDataHolder.status = SyncStatus.SYNCHRONIZING;
            mDataHolder.data = sync();
            mDataHolder.status = SyncStatus.SYNCHRONIZED;
            mDataHolder.syncTime = System.currentTimeMillis();
        } catch (Exception e) {
            Log.e(TAG, "sync. error " + getId() + ", url [" + getUrl() + "]", e);
            mDataHolder.status = SyncStatus.ERROR;
        } finally {
            Log.d(TAG, "sync. stop " + getId());
        }
        return mDataHolder.status;
    }

    protected abstract List<T> sync() throws Exception;

    static String getId(final Class clazz) {
        return clazz.getSimpleName();
    }
}
