package com.wsi.mvc;

import android.app.Activity;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Ievgen Usenko
 * Date: 6/27/14.
 */
public class ExampleActivity extends Activity {

    private TextView mExampleEntityView;
    private ExampleDataProvider mDataProvider;
    private ContentObserver mExampleEntityObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataProvider = new ExampleDataProvider(getApplicationContext());
        mExampleEntityObserver = new ExampleEntityObserver();
        mExampleEntityView = new TextView(this);

        setContentView(mExampleEntityView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getContentResolver().registerContentObserver(ExampleEntity.CONTENT_URI, false, mExampleEntityObserver);
        mDataProvider.sync();
    }

    @Override
    protected void onPause() {
        getContentResolver().unregisterContentObserver(mExampleEntityObserver);
        super.onPause();
    }

    private class ExampleEntityObserver extends ContentObserver {
        private ExampleEntityObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            /**
             * Notification was sent from the external thread, but we
             * can touch main thread(or UI thread) from the observer.
             */
            ExampleEntity entity = mDataProvider.get(0);
            mExampleEntityView.setText(entity.name);
        }
    }
}
