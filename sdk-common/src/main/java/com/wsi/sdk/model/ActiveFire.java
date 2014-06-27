package com.wsi.sdk.model;

import android.net.Uri;

/**
 * Created by Ievgen Usenko
 * Date: 6/26/14.
 */
public class ActiveFire extends AbstractEntity {
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("activeFire").build();
}
