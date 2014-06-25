package com.wsi.sdk.provider;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
abstract class AbstractSyncCallable<T> implements Callable<SyncStatus> {
    private static final String TAG = AbstractSyncCallable.class.getSimpleName();
    private static final String DEFAULT_BASE_URL = "http://base-backend-url-stub";

    private final Context mContext;
    private final DataHolder<T> mDataHolder;

    protected AbstractSyncCallable(Context mContext, DataHolder<T> mDataHolder) {
        this.mContext = mContext;
        this.mDataHolder = mDataHolder;
    }

    protected String getBaseUrl() {
        return DEFAULT_BASE_URL;
    }

    protected abstract String getUrl();

    protected abstract String getId();

    @Override
    public SyncStatus call() throws Exception {
        try {
            Log.d(TAG, "sync. start " + getId());
            mDataHolder.status = SyncStatus.SYNCHRONIZING;
            mDataHolder.data = sync();
            mDataHolder.status = SyncStatus.SYNCHRONIZED;
            mDataHolder.syncTime = System.currentTimeMillis();
            mContext.getContentResolver().notifyChange(mDataHolder.CONTENT_URI, null);
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
