package com.wsi.sdk.sync;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
abstract class AbstractSyncTask {
    private static final String DEFAULT_BASE_URL = "http://base-backend-url-stub";

    protected String getBaseUrl() {
        return DEFAULT_BASE_URL;
    }

    abstract String getUrl();

    abstract boolean perform();
}
