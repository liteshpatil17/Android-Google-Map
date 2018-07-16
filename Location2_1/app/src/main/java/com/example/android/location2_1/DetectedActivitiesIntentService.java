package com.example.android.location2_1;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

public class DetectedActivitiesIntentService extends IntentService {
    protected final String TAG="detection_is";
    public DetectedActivitiesIntentService()
    {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ActivityRecognitionResult result =ActivityRecognitionResult.extractResult(intent);
        Intent localIntent=new Intent(Constants.BROADCAST_ACTION);
        ArrayList<DetectedActivity> detectedActivities =(ArrayList<DetectedActivity>) result.getProbableActivities();
        Log.i(TAG,"activities detected");
        localIntent.putExtra(Constants.ACTIVITY_EXTRA,detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
