package org.bostonandroid.datepreferencetest;

import java.util.Calendar;
import java.util.Date;

import org.bostonandroid.datepreference.DatePreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class DatePreferenceActivity extends PreferenceActivity {
  private Bundle savedInstanceState;
  
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.savedInstanceState = savedInstanceState;
    addPreferencesFromResource(R.xml.preferences);
  }
  
  // This is used by the test.
  public Bundle getBundle() {
    return savedInstanceState;
  }

  // This is used by the test.
  public Date getDateOfDeath() {
    return DatePreference.getDateFor(preferences(),"dod");
  }
  
  // This is used by the test.
  public Calendar getCalendarOfDeath() {
    return DatePreference.getCalendarFor(preferences(),"dod");
  }
  
  // This is used by the test.
  public Date getDateOfArrival() {
    return DatePreference.getDateFor(preferences(),"doa");
  }
  
  // This is used by the test.
  public Calendar getCalendarOfArrival() {
    return DatePreference.getCalendarFor(preferences(),"doa");
  }
  
  private SharedPreferences preferences() {
    return PreferenceManager.getDefaultSharedPreferences(this);
  }
  
  protected void onResume() {
    Log.i("DatePreferenceActivity", "onResume()");
    super.onResume();
    
    SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
    Log.i("DatePreferenceActivity", prefs.getString("dod", DatePreference.defaultDateString()));
    getPreference("dod").setDate(prefs.getString("dod", DatePreference.defaultDateString()));
  }
  
  protected void onPause() {
    Log.i("DatePreferenceActivity", "onPause()");
    super.onPause();
    
    SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
    Log.i("DatePreferenceActivity", DatePreference.formatter().format(getDateOfDeath()));
    editor.putString("dod", DatePreference.formatter().format(getDateOfDeath()));
    editor.commit();
  }
  
  private DatePreference getPreference(String key) {
    return (DatePreference)getPreferenceManager().findPreference(key);
  }
}
