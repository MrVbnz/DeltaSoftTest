package ru.vbnz.deltasofttest;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class FirebaseWrapper {
    private static final String APP_PREFERENCES = "DeltaSoftTestPrefs";
    private static final String APP_PREFERENCES_URL = "PrefUrl";
    private SharedPreferences mSharedPreferences;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public FirebaseWrapper(AppCompatActivity activity) {
        mSharedPreferences = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        ConfigureFirebase();
    }

    public String GetUrl() {
        String url = GetLocalUrl();
        if (url.isEmpty()) url = mFirebaseRemoteConfig.getString("url");
        if (!url.isEmpty()) SetLocalUrl(url);
        return url;
    }

    private void SetLocalUrl(String url) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(APP_PREFERENCES_URL, url);
        editor.apply();
    }

    private String GetLocalUrl() {
        return mSharedPreferences.getString(APP_PREFERENCES_URL, "");
    }

    private void ConfigureFirebase(){
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(120)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        mFirebaseRemoteConfig.fetchAndActivate();
    }
}
