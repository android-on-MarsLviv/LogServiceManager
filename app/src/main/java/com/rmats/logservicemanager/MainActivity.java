package com.rmats.logservicemanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.P)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String processName = Application.getProcessName();

    TextView textView;
    Button button;

    private ILogger logService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            logService = ILogger.Stub.asInterface(service);
            Log.i(TAG, "logService connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            logService = null;
            Log.i(TAG, "logService disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: Process name: " + processName);

        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);

        button.setOnClickListener(this::onClickButton);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, LoggerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unbindService(connection);
        logService = null;
    }

    private void onClickButton(View view) {
        try {
            logService.log(new LogLevel(), "112");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}