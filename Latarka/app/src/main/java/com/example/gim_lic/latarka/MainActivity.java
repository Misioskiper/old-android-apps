package com.example.gim_lic.latarka;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    public Camera cam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Switch light = (Switch)findViewById(R.id.suwak);

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    try{
                        CameraManager camMan = (CameraManager)getApplicationContext().getSystemService(Context.CAMERA_SERVICE);

                        for(String id : camMan.getCameraIdList())
                        {
                            if (camMan.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE))
                            {
                               camMan.setTorchMode(id, isChecked);
                            }
                        }
                    }catch (Exception e2)
                    {

                    }
                }
                else {


                    if (cam == null) {
                        cam = Camera.open();
                    }
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    if (isChecked) {
                        cam.startPreview();
                    } else {
                        cam.stopPreview();
                        cam.release();
                        cam = null;
                    }
                }
            }
        });
    }
}
