package com.auebds.coffeui.util;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.auebds.coffeui.dao.SettingsDao;

import java.util.Locale;

/**
 * A singleton wrapper around a TTS device, made to circumvent its long initialization times.
 * @author Dimitris Tsirmpas
 */
public class SingletonTTS {
    private static SingletonTTS instance;

    private final SettingsDao settings;
    private final TextToSpeech mTTS;

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    private SingletonTTS(Context context, SettingsDao settings, String initialMessage) {
        this.mTTS = new TextToSpeech(context, i -> {
            configTTS();

            if(initialMessage != null) {
                speakSentence(initialMessage);
            }
        });
       this.settings = settings;
    }

    /**
     * Get the active instance of the SingletonTTS device.
     * @param context the base application context
     * @return the active TTS object
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public static synchronized SingletonTTS getInstance(Context context, SettingsDao settings,
                                                        String initialMessage) {
        if (instance == null) {
            instance = new SingletonTTS(context, settings, initialMessage);
        }
        return instance;
    }

    /**
     * Get the active instance of the SingletonTTS device.
     * @param context the base application context
     * @return the active TTS object
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public static synchronized SingletonTTS getInstance(Context context, SettingsDao settings) {
        return SingletonTTS.getInstance(context, settings, null);
    }


    /**
     * Instruct the TTS device to speak a sentence, if voice activation is enabled.
     * @param sentence the sentence to be spoken
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public void speakSentence(String sentence){
        settings.isVoiceOn().subscribe(isVoiceAllowed -> {
            if (isVoiceAllowed) {
                mTTS.speak(sentence, TextToSpeech.QUEUE_ADD, null, null);
            }
        });

    }

    private void configTTS() {
        int available = mTTS.isLanguageAvailable(Locale.getDefault());
        if(available != TextToSpeech.LANG_MISSING_DATA && available != TextToSpeech.LANG_NOT_SUPPORTED){
            mTTS.setLanguage(new Locale(Locale.getDefault().getLanguage()) );
        }
    }
}