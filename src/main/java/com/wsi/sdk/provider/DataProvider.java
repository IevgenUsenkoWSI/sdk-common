package com.wsi.sdk.provider;

import android.content.Context;
import android.os.Handler;

import com.wsi.sdk.model.AbstractEntity;
import com.wsi.sdk.model.ActiveFire;
import com.wsi.sdk.model.FireRisk;

import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public class DataProvider {
    private static final Handler sHandler = new Handler();

    static final DataHolder<ActiveFire> sActiveFireHolder = new DataHolder<ActiveFire>();
    static final DataHolder<FireRisk> sFireRiskHolder = new DataHolder<FireRisk>();

    public static List<ActiveFire> getActiveFires() {
        return sActiveFireHolder.data;
    }

    public static List<FireRisk> getFireRisks() {
        return sFireRiskHolder.data;
    }

    public static SyncStatus getStatus(Class<? extends AbstractEntity> entityClass) {
        if (entityClass.equals(ActiveFire.class)) {
            return sActiveFireHolder.status;
        } else if (entityClass.equals(FireRisk.class)) {
            return sFireRiskHolder.status;
        } else {
            throw new UnsupportedOperationException("unsupported type " + entityClass);
        }
    }

    public static void sync(final Context ctx) {
        sync(ctx, null);
    }

    public static void sync(final Context ctx, Class<? extends AbstractEntity> entityClass) {
        if (ctx == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        SyncService.sync(ctx, entityClass);
    }

    private static boolean isSyncAllowed(long syncTime, long currentTime) {
        return true;
    }
}
