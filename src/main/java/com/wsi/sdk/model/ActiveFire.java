package com.wsi.sdk.model;

import android.net.Uri;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
public class ActiveFire extends AbstractEntity {
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("activeFire").build();
}
