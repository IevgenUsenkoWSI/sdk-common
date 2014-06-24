package com.wsi.sdk.model;

import android.net.Uri;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public abstract class AbstractEntity {
    public static final String CONTENT_AUTHORITY = "com.wsi.sdk";
    protected static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public abstract Uri getContentUri();
}
