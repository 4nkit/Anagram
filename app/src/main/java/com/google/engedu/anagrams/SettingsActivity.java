package com.google.engedu.anagrams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceManager;

public class SettingsActivity extends AppCompatPreferenceActivity {

    Intent intent;
    int count=0;
    ListPreference lp;
    SharedPreferences sp;
    private PreferenceChangeListener preferenceChangeListener=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_screen);
        lp = (ListPreference)findPreference("letter");
        lp.setDefaultValue("One letter");
        sp= PreferenceManager.getDefaultSharedPreferences(this);
        preferenceChangeListener=new PreferenceChangeListener();
        sp.registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        ApplySettings();

    }

    private class PreferenceChangeListener implements
            SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences prefs,
                                              String key) {
            //  Toast.makeText(SettingsActivity.this, "hello", Toast.LENGTH_SHORT).show();
            ApplySettings();
        }
    }

    public void ApplySettings() {
        count++;
        String words = sp.getString("letter", "One letter");
        //Toast.makeText(this, Integer.toString(count), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, dice, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, player, Toast.LENGTH_SHORT).show();

        if (words.equals("One letter")) {
            intent = new Intent(this, Anagram1Activity.class);
            lp.setSummary("One letter");
        } else {
            intent = new Intent(this, Anagram2Activity.class);
            lp.setSummary("Two letter");
        }


    }

    @Override
    public void onBackPressed() {
        startActivity(intent);
    }
}

