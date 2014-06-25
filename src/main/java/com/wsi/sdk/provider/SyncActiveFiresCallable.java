package com.wsi.sdk.provider;

import android.content.Context;

import com.wsi.sdk.model.ActiveFire;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
class SyncActiveFiresCallable extends AbstractSyncCallable<ActiveFire> {

    SyncActiveFiresCallable(Context mContext, DataHolder<ActiveFire> mDataHolder) {
        super(mContext, mDataHolder);
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected String getId() {
        return getId(ActiveFire.class);
    }

    @Override
    protected List<ActiveFire> sync() throws Exception {
        return Collections.EMPTY_LIST;
    }
}
