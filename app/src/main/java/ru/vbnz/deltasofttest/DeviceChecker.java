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
        if (!IsGsm())
            return false;
        if (!HasSim())
            return false;
        return true;
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
