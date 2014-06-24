package com.wsi.sdk.provider;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
class DataHolder<T> {
    List<T> data = Collections.EMPTY_LIST;
    long syncTime;
    SyncStatus status = SyncStatus.UNDEFINED;
}
