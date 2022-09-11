package ru.vbnz.deltasofttest;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.appcompat.app.AppCompatActivity;

public class DeviceChecker {

    private TelephonyManager mTelephonyManager = null;

    public DeviceChecker(AppCompatActivity activity) {
        mTelephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public Boolean IsUserDevice() {
        if (ContainsGoogle())
            return false;
        if (isEmulator())
            return false;
        if (!IsGsm())
            return false;
        if (!HasSim())
            return false;
        return true;
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    private Boolean IsGsm() {
        int type = mTelephonyManager.getPhoneType();
        return type == TelephonyManager.PHONE_TYPE_GSM;
    }

    private Boolean HasSim() {
        int simState = mTelephonyManager.getSimState();
        return simState == TelephonyManager.SIM_STATE_READY;
    }

    private Boolean ContainsGoogle() {
        String devModel = Build.MODEL;
        String devManufacturer = Build.MANUFACTURER;
        String devName = Build.DEVICE;
        return (devModel+devManufacturer+devName).toLowerCase().contains("google");
    }
}
