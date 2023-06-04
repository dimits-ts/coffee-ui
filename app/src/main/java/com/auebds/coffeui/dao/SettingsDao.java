package com.auebds.coffeui.dao;

import android.content.Context;
import android.util.Log;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * A wrapper-class around an Android DataStore, used to access user-settings.
 * @author Dimitris Tsirmpas
 */
public class SettingsDao {
    private static final String SETTINGS_FILE_NAME = "settings";
    private static final Preferences.Key<Boolean> VOICE_ON_KEY = PreferencesKeys.booleanKey("VOICE_ON");
    private static final Boolean DEFAULT_VOICE_ON = true;

    private static SettingsDao settingsDao;

    private final RxDataStore<Preferences> dataStore;

    /**
     * Get the global instance of the SettingsDao.
     * @param context the application context
     * @return the settings dao instance
     */
    public static SettingsDao getInstance(Context context) {
        if(SettingsDao.settingsDao == null) {
            SettingsDao.settingsDao = new SettingsDao(
                    new RxPreferenceDataStoreBuilder(context, SETTINGS_FILE_NAME).build());
        }
        return SettingsDao.settingsDao;
    }

    /**
     * Construct a new SettingsDao.
     * @param dataStore the internal datastore used
     */
    public SettingsDao(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Check if TTS voice is activated.
     * @return true if TTS is activated, false otherwise
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public Flowable<Boolean> isVoiceOn() {
        return dataStore.data()
                .map(prefs -> prefs.get(VOICE_ON_KEY) == null ? DEFAULT_VOICE_ON
                        : prefs.get(VOICE_ON_KEY));
    }

    /**
     * Switch the user preference for TTS voice narration.
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public void switchVoice() {
        dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutPrefsIn = prefsIn.toMutablePreferences();
            boolean isActivated = Boolean.TRUE.equals(prefsIn.get(VOICE_ON_KEY));
            mutPrefsIn.set(VOICE_ON_KEY, !isActivated);

            Log.i("SETTINGS", "Voice is now " + (!isActivated ? "activated": "deactivated"));
            return Single.just(mutPrefsIn);
        });
    }

}
