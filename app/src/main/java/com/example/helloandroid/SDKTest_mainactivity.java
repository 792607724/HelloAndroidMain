package com.example.helloandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seevi.SVCameraDevice.SVCameraDevice;
import com.seevi.SVCameraDevice.SVCameraDeviceInfo;

import java.util.List;

public class SDKTest_mainactivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "======= Guangtao =======";
    private Button btn_enumDevice, btn_openSpecificDevice, btn_readSoftwareVersion,
            btn_readCameraModuleName, btn_zoom, btn_move, btn_isSupportEPTZ, btn_getEPTZ,
            btn_setEPTZ, btn_getEPTZmode, btn_setEPTZmode;
    private TextView tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdktest_mainactivity);
        initView();

    }


    private void initView() {
        btn_enumDevice = findViewById(R.id.btn_enumDevice);
        btn_openSpecificDevice = findViewById(R.id.btn_openSpecificDevice);
        btn_readSoftwareVersion = findViewById(R.id.btn_readSoftwareVersion);
        btn_readCameraModuleName = findViewById(R.id.btn_readCameraModuleName);
        btn_zoom = findViewById(R.id.btn_zoom);
        btn_move = findViewById(R.id.btn_move);
        btn_isSupportEPTZ = findViewById(R.id.btn_isSupportEPTZ);
        btn_getEPTZ = findViewById(R.id.btn_getEPTZ);
        btn_setEPTZ = findViewById(R.id.btn_setEPTZ);
        btn_getEPTZmode = findViewById(R.id.btn_getEPTZmode);
        btn_setEPTZmode = findViewById(R.id.btn_setEPTZmode);

        tv_result = findViewById(R.id.tv_result);

        btn_enumDevice.setOnClickListener(this);
        btn_openSpecificDevice.setOnClickListener(this);
        btn_readSoftwareVersion.setOnClickListener(this);
        btn_readCameraModuleName.setOnClickListener(this);
        btn_zoom.setOnClickListener(this);
        btn_move.setOnClickListener(this);
        btn_isSupportEPTZ.setOnClickListener(this);
        btn_getEPTZ.setOnClickListener(this);
        btn_setEPTZ.setOnClickListener(this);
        btn_getEPTZmode.setOnClickListener(this);
        btn_setEPTZmode.setOnClickListener(this);
    }

    private void test_enumDevice() {
        List<SVCameraDeviceInfo> enumDevice = SVCameraDevice.enumDevice(this);
        for (int i = 0; i < enumDevice.size(); i++) {
            Log.i(TAG, "test_enumDevice: " + enumDevice.get(i));
        }
        tv_result.setText(enumDevice.toString() + " size:" + enumDevice.size());
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_enumDevice:
                Toast.makeText(this, "btn_enumDevice - ???????????????CameraDevice??????", Toast.LENGTH_SHORT).show();
                test_enumDevice();
                break;
            case R.id.btn_openSpecificDevice:
                Toast.makeText(this, "btn_openSpecificDevice - ??????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_readSoftwareVersion:
                Toast.makeText(this, "btn_readSoftwareVersion - ??????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_readCameraModuleName:
                Toast.makeText(this, "btn_readCameraModuleName - ???????????????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_zoom:
                Toast.makeText(this, "btn_zoom - ????????????, zoom_type????????????. step????????????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_move:
                Toast.makeText(this, "btn_move - ???????????????. direction ?????????????????????. step??????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_isSupportEPTZ:
                Toast.makeText(this, "btn_isSupportEPTZ - ??????????????????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_getEPTZ:
                Toast.makeText(this, "btn_getEPTZ - ????????????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_setEPTZ:
                Toast.makeText(this, "btn_setEPTZ - ????????????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_getEPTZmode:
                Toast.makeText(this, "btn_getEPTZmode - ????????????????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_setEPTZmode:
                Toast.makeText(this, "btn_setEPTZmode - ????????????????????????", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}