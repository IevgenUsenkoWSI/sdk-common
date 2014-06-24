package com.wsi.sdk.provider;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.wsi.sdk.model.AbstractEntity;
import com.wsi.sdk.model.ActiveFire;
import com.wsi.sdk.model.FireRisk;

import static com.wsi.sdk.provider.AbstractSyncTask.getId;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public class SyncService extends IntentService {
    private static final String TAG = SyncService.class.getSimpleName();

    static final String ACTION_SYNC = "action-sync-all";
    static final String EXTRA_ID = "extra-id";

    public SyncService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String action = intent.getAction();
        final String id = intent.getStringExtra(EXTRA_ID);
        final Context ctx = getApplicationContext();

        if (!ACTION_SYNC.equals(ACTION_SYNC)) {
            throw new UnsupportedOperationException("invalid action " + action);
        }

        if (id == null /* sync all */) {
            new SyncActiveFiresTask(ctx, DataProvider.sActiveFireHolder).perform();
            new SyncFireRisksTask(ctx, DataProvider.sFireRiskHolder).perform();
        } else if (id.equals(getId(ActiveFire.class))) {
            new SyncActiveFiresTask(ctx, DataProvider.sActiveFireHolder).perform();
        } else if (id.equals(getId(FireRisk.class))) {
            new SyncFireRisksTask(ctx, DataProvider.sFireRiskHolder).perform();
        } else {
            throw new UnsupportedOperationException("invalid id " + action);
        }
    }

    /**
     * Schedule syncing for all types.
     *
     * @param ctx
     */
    static void sync(final Context ctx) {
        sync(ctx, null);
    }

    /**
     * Schedule syncing for specified type.
     *
     * @param ctx
     * @param entityClass
     */
    static void sync(final Context ctx, final Class<? extends AbstractEntity> entityClass) {

        Intent intent = new Intent(ctx, SyncService.class);
        intent.setAction(ACTION_SYNC);
        if (entityClass != null) {
            intent.putExtra(EXTRA_ID, getId(entityClass));
        }
        ctx.startService(intent);
    }
}
