package com.rmats.logservicemanager;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.P)
public class LoggerService extends Service {
    private static final String TAG = LoggerService.class.getSimpleName();
    private static final String processName = Application.getProcessName();

    @Override
    public IBinder onBind(Intent intent) { return binder; }

    ILogger.Stub binder = new ILogger.Stub() {
        @Override
        public void log(LogLevel logLevel, String message) throws RemoteException {
            Log.i(TAG, message);
            Log.i(TAG, "Process name: " + processName);
        }
    };
}