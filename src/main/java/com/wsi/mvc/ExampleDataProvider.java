package com.wsi.mvc;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ievgen Usenko
 * Date: 6/27/14.
 */
public class ExampleDataProvider {
    private static final String TAG = ExampleDataProvider.class.getSimpleName();

    private List<ExampleEntity> mExampleEntities = Collections.EMPTY_LIST;
    private final Context mContext;

    public ExampleDataProvider(Context mContext) {
        this.mContext = mContext;
    }

    public void set(final List<ExampleEntity> data) {
        if (data == null) throw new IllegalArgumentException("data must not be null");
        mExampleEntities = data;
    }

    public List<ExampleEntity> get() {
        return mExampleEntities;
    }

    public ExampleEntity get(int index) {
        if (index < 0 || index >= mExampleEntities.size()) {
            throw new IllegalArgumentException("invalid index=" + index);
        }
        return mExampleEntities.get(index);
    }

    public void sync() {
        new SimulateNetworkingRequestThread(mContext, this).start();
    }

    private static class SimulateNetworkingRequestThread extends Thread {
        final Context ctx;
        final ExampleDataProvider dataProvider;

        private SimulateNetworkingRequestThread(Context ctx, ExampleDataProvider dataProvider) {
            this.ctx = ctx;
            this.dataProvider = dataProvider;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                ExampleEntity entity = new ExampleEntity(UUID.randomUUID().toString());
                List<ExampleEntity> data = new ArrayList<ExampleEntity>(1);
                data.add(entity);

                /**
                 * All magic starts here. We set data to provider and notify all observers.
                 * Observer just need to call 'dataProvider.get()'
                 */
                dataProvider.set(data);
                ctx.getContentResolver().notifyChange(ExampleEntity.CONTENT_URI, null);
            } catch (InterruptedException e) {
                Log.e(TAG, "networking thread is interrupted", e);
            }
        }
    }
}
