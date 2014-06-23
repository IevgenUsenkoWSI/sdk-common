package com.wsi.sdk.sync;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public class SyncService extends IntentService {
    private static final String TAG = SyncService.class.getSimpleName();

    public static final String ACTION_SYNC_ALL = "action-sync-all";
    public static final String ACTION_SYNC_FIRE_RISK = "action-sync-fire-risk";
    public static final String ACTION_SYNC_ACTIVE_FIRES = "action-sync-action-fire";

    public SyncService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String action = intent.getAction();
        Log.d(TAG, "action [" + action + "]");

        if (ACTION_SYNC_FIRE_RISK.equals(action)) {
            new SyncFireRisksTask().perform();
        } else if (ACTION_SYNC_ACTIVE_FIRES.equals(action)) {
            new SyncActiveFiresTask().perform();
        } else if (ACTION_SYNC_ALL.equals(action)) {
            new SyncActiveFiresTask().perform();
            new SyncFireRisksTask().perform();
        } else {
            throw new UnsupportedOperationException("invalid action " + action);
        }
    }
}
