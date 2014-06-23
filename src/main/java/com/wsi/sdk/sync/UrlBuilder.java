package com.wsi.sdk.sync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ievgen Usenko on 6/23/14.
 */
class UrlBuilder {

    private static final String DEFAULT_BASE_URL = "http://base-backend-url-stub";

    private final String mBaseUrl;
    private final List<String> mPaths = new ArrayList<String>();
    private final Map<String, String> mParameters = new HashMap<String, String>();

    private UrlBuilder(String url) {
        this.mBaseUrl = url;
    }

    static UrlBuilder wsi() {
        return new UrlBuilder(DEFAULT_BASE_URL);
    }

    static UrlBuilder create(final String baseUrl) {
        return new UrlBuilder(baseUrl);
    }

    UrlBuilder path(final String path) {
        mPaths.add(path);
        return this;
    }

    UrlBuilder parameter(final String key, final String value) {
        mParameters.put(key, value);
        return this;
    }

    UrlBuilder parameters(final Map<String, String> parameters) {
        parameters.putAll(parameters);
        return this;
    }

    String get() {
        StringBuilder url = new StringBuilder(mBaseUrl);
        for (String path : mPaths) {
            url.append("/").append(path);
        }

        return url.toString();
    }
}
