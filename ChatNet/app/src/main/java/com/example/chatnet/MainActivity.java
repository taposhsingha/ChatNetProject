package com.example.chatnet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button enablebluetooth, signout;
    BluetoothAdapter myBluetoothAdapter;
    TextView welcomeText;

    Intent bluetoothEnableIntent;
    int requestCodeForEnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enablebluetooth = (Button) findViewById(R.id.enablebluetooth);
        signout = (Button) findViewById(R.id.signout);
        welcomeText = (TextView) findViewById(R.id.welcometext);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothEnableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForEnable = 1;
        enablebluetooth();
        signout();
        welcomeText.setText("Welcome "+ userlogin.username);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCodeForEnable) {
            if (requestCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Bluetooth is enabled", Toast.LENGTH_SHORT).show();
            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Enable bluetooth cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void enablebluetooth() {
        enablebluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myBluetoothAdapter==null) {
                    Toast.makeText(getApplicationContext(),"No bluetooth in this device",Toast.LENGTH_SHORT).show();
                } else {
                    if (!myBluetoothAdapter.isEnabled()){
                        startActivityForResult(bluetoothEnableIntent,requestCodeForEnable);
                        Intent sendIntent = new Intent(MainActivity.this,DeviceList.class);
                        startActivity(sendIntent);
                    }
                }
            }
        });
    }

    public void signout(){
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutintent = new Intent(MainActivity.this,login.class);
                startActivity(logoutintent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!userlogin.userlogin){
            Intent newIntent = new Intent(MainActivity.this,login.class);
            startActivity(newIntent);
        }
    }

}