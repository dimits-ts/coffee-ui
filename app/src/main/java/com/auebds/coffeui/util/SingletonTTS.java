package com.auebds.coffeui.util;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.auebds.coffeui.dao.SettingsDao;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * A singleton wrapper around a TTS device, made to circumvent its long initialization times.
 * @author Dimitris Tsirmpas
 */
public class SingletonTTS {
    private static SingletonTTS instance;

    private final SettingsDao settings;
    private final TextToSpeech mTTS;
    private final Set<String> alreadySpokenSentences = new HashSet<>();

    /**
     * Get the active instance of the SingletonTTS device.
     * @param context the base application context
     * @param settings a settings object to determine if voice activation is enabled
     * @param initialMessage a message to be spoken after initialization is complete. Uses
     *                       {@link SingletonTTS#speakOnce(String)}.
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
     * @param settings a settings object to determine if voice activation is enabled
     * @return the active TTS object
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public static synchronized SingletonTTS getInstance(Context context, SettingsDao settings) {
        return SingletonTTS.getInstance(context, settings, null);
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    private SingletonTTS(Context context, SettingsDao settings, String initialMessage) {
        this.mTTS = new TextToSpeech(context, i -> {
            configTTS();

            if(initialMessage != null) {
                speakOnce(initialMessage);
            }
        });
        this.settings = settings;
    }

    /**
     * Instruct the TTS device to speak a sentence, if voice activation is enabled.
     * @param sentence the sentence to be spoken
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public void speakSentence(String sentence){
        settings.isVoiceOn().subscribe(isVoiceAllowed -> {
            if (isVoiceAllowed) {
                mTTS.speak(sentence, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

    }

    /**
     * Instruct the TTS device to speak a sentence if the sentence was not already spoken, and
     * if voice activation is enabled.
     * Sentences spoken by {@link SingletonTTS#speakSentence(String)} are not counted as "already spoken".
     * @param sentence the sentence to be spoken
     */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    public void speakOnce(String sentence) {
        if(!alreadySpokenSentences.contains(sentence)) {
            speakSentence(sentence);
            this.alreadySpokenSentences.add(sentence);
        }
    }

    private void configTTS() {
        int available = mTTS.isLanguageAvailable(Locale.getDefault());
        if(available != TextToSpeech.LANG_MISSING_DATA && available != TextToSpeech.LANG_NOT_SUPPORTED){
            mTTS.setLanguage(new Locale(Locale.getDefault().getLanguage()) );
        }
    }
}