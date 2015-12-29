package com.example.android.sunshine.app;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.WearableListenerService;

public class SunshineWatchFaceService extends WearableListenerService {
    private static final String TAG = SunshineWatchFaceService.class.getSimpleName();
    private static final String WEATHER_PATH = "/weather";

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (DataEvent.TYPE_CHANGED == dataEvent.getType()) {
                String path = dataEvent.getDataItem().getUri().getPath();
                if (path.equals(WEATHER_PATH)) {
                    SunshineSyncAdapter.syncImmediately(this);
                }
            }
        }
    }
}