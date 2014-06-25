package com.wsi.sdk.provider;

import android.content.Context;
import android.os.Handler;

import com.wsi.sdk.model.AbstractEntity;
import com.wsi.sdk.model.ActiveFire;
import com.wsi.sdk.model.FireRisk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public class DataProvider {
    private static final Handler sHandler = new Handler();

    private static final DataHolder<ActiveFire> sActiveFireHolder = new DataHolder<ActiveFire>(ActiveFire.CONTENT_URI);
    private static final DataHolder<FireRisk> sFireRiskHolder = new DataHolder<FireRisk>(FireRisk.CONTENT_URI);

    private static ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private static final Map<Class<? extends AbstractEntity>, Future> sPendingTasks = new HashMap<Class<? extends AbstractEntity>, Future>();

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
        if (ctx == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        Future<SyncStatus> future;
        future = EXECUTOR.submit(new SyncActiveFiresCallable(ctx, sActiveFireHolder));


        future = EXECUTOR.submit(new SyncFireRisksCallable(ctx, sFireRiskHolder));
    }

    public static void sync(final Context ctx, Class<? extends AbstractEntity> entityClass) {
        if (ctx == null) {
            throw new IllegalArgumentException("context must not be null");
        }

        Future<SyncStatus> future;
        if (ActiveFire.class.equals(entityClass)) {
            future = EXECUTOR.submit(new SyncActiveFiresCallable(ctx, sActiveFireHolder));
        } else if (FireRisk.class.equals(entityClass)) {
            future = EXECUTOR.submit(new SyncFireRisksCallable(ctx, sFireRiskHolder));
        } else {
            throw new IllegalArgumentException("invalid entity class " + entityClass);
        }
    }

    public static void cancelSync(final Class<? extends AbstractEntity> entityClass) {

    }

    public static void cancelSync() {

    }

    private static boolean isSyncAllowed(long syncTime, long currentTime) {
        return true;
    }
}
