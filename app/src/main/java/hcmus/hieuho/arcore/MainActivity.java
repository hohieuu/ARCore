package hcmus.hieuho.arcore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.ar.core.ArCoreApk;

import hcmus.hieuho.arcore.helpers.CameraPermissionHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maybeEnableArButton();

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            CameraPermissionHelper.requestCameraPermission(this);
            return;
        }

    }

    void maybeEnableArButton() {
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
        if (availability.isTransient()) {
            // Continue to query availability at 5Hz while compatibility is checked in the background.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    maybeEnableArButton();
                }
            }, 200);
        }
//        if (availability.isSupported()) {
//            mArButton.setVisibility(View.VISIBLE);
//            mArButton.setEnabled(true);
//        } else { // The device is unsupported or unknown.
//            mArButton.setVisibility(View.INVISIBLE);
//            mArButton.setEnabled(false);
//        }
    }
}