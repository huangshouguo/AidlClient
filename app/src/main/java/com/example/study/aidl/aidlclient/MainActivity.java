package com.example.study.aidl.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.study.aidl.aidlserver.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface myAidlInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("com.example.study.aidl.MyServer");
        intent.setPackage("com.example.study.aidl.aidlserver");

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = myAidlInterface.getName();
                    String user = myAidlInterface.getUser().toString();

                    Toast.makeText(MainActivity.this, name + ", " + user, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
