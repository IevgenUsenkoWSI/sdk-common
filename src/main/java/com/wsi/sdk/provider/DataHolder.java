package com.wsi.sdk.provider;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
class DataHolder<T> {

    final Uri CONTENT_URI;

    List<T> data = Collections.EMPTY_LIST;
    long syncTime;
    SyncStatus status = SyncStatus.UNDEFINED;

    DataHolder(Uri CONTENT_URI) {
        this.CONTENT_URI = CONTENT_URI;
    }
}
