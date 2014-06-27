package com.wsi.mvc;

import android.net.Uri;

/**
 * Created by Ievgen Usenko
 * Date: 6/27/14.
 */
public class ExampleEntity {
    /**
     * Every entity declares <code>public static CONTENT_URI</code> which is used as
     * part of the notification mechanism.
     */
    public static final Uri CONTENT_URI = Uri.parse("content://example-entity");

    /**
     * Just for example purpose.
     */
    public final String name;

    public ExampleEntity(String name) {
        this.name = name;
    }
}
