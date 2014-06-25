package com.wsi.sdk.provider;

import android.content.Context;

import com.wsi.sdk.model.FireRisk;

import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
class SyncFireRisksCallable extends AbstractSyncCallable<FireRisk> {
    SyncFireRisksCallable(Context mContext, DataHolder<FireRisk> mDataHolder) {
        super(mContext, mDataHolder);
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected String getId() {
        return null;
    }

    @Override
    protected List<FireRisk> sync() throws Exception {
        return null;
    }
}
