package com.wsi.sdk.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;

import com.wsi.sdk.model.ActiveFire;
import com.wsi.sdk.model.FireRisk;
import com.wsi.sdk.sync.SyncStatus;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public class DataProvider {
    private static final Handler sHandler = new Handler();

    private static final DataHolder<ActiveFire> sActiveFiresHolder = new DataHolder<ActiveFire>();
    private static final DataHolder<FireRisk> sFireRiskHolder = new DataHolder<FireRisk>();

    public static class ContentObserverAdapter extends ContentObserver {
        private final Uri mUri;

        public ContentObserverAdapter(final Uri uri) {
            super(sHandler);
            mUri = uri;
        }

        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, mUri);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            if (ActiveFire.CONTENT_URI.equals(uri)) {
                onActiveFiresChanged(sActiveFiresHolder.data);
            } else if (FireRisk.CONTENT_URI.equals(uri)) {
                onFireRisksChanged(sFireRiskHolder.data);
            } else {
                throw new IllegalArgumentException("unsupported URI " + uri);
            }
        }

        protected void onActiveFiresChanged(final List<ActiveFire> data) {
        }

        protected void onFireRisksChanged(final List<FireRisk> data) {
        }
    }
}
