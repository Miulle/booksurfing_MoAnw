package com.htwg.booksurfing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyPreferenceFragment extends android.preference.PreferenceFragment {
    private static final String TAG = "PreferenceFragment";

    public static final String PREF_API_KEY = "pref.api.key";

    public static final String USERNAME = "Mikael";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);


        view.setBackgroundColor(getResources().getColor(android.R.color.white));
//        view.

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
        return view;
    }
}