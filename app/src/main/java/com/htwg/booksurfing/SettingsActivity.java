package com.htwg.booksurfing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity {
    private static final String TAG = "PreferenceActivity";

    public static final String PREF_API_KEY = "pref.api.key";

    public static final String USERNAME = "Mikael";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();


//        addPreferencesFromResource(R.xml.preferences);
//
//        Preference prefKey = findPreference("pref_api_key");
//        Preference prefUserName = findPreference("username");
//        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        prefKey.setDefaultValue(mSettings.getString(PREF_API_KEY, getString(R.string.pref_default_display_name)));
//        prefUserName.setDefaultValue(mSettings.getString(USERNAME, getString(R.string.pref_title_display_username)));
//
//
//        prefKey.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                Log.d(TAG, "API Key has changed");
//                SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = mSettings.edit();
//
//                editor.putString(PREF_API_KEY, newValue.toString());
//                editor.apply();
//                return true;
//            }
//        });

    }

    public static class MyPreferenceFragment extends android.preference.PreferenceFragment {
        private static final String TAG = "PreferenceFragment";

        public static final String PREF_API_KEY = "pref.api.key";

        public static final String USERNAME = "Mikael";

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            Preference prefKey = findPreference("pref_api_key");
            Preference prefUserName = findPreference("username");
            final SharedPreferences mSettings = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
            prefKey.setDefaultValue(mSettings.getString(PREF_API_KEY, getString(R.string.pref_default_display_name)));
            prefUserName.setDefaultValue(mSettings.getString(USERNAME, getString(R.string.pref_title_display_username)));


            prefKey.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    Log.d(TAG, "API Key has changed");
                    //SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = mSettings.edit();

                    editor.putString(PREF_API_KEY, newValue.toString());
                    editor.apply();
                    return true;
                }
            });

        }

    }
}
