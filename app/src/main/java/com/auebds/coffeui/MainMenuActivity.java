package com.auebds.coffeui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.auebds.coffeui.dao.SettingsDao;
import com.auebds.coffeui.databinding.ActivityMainMenuBinding;
import com.auebds.coffeui.ui.drinks.chocolate.CreateChocolateActivity;
import com.auebds.coffeui.ui.drinks.espresso.CreateEspressoActivity;
import com.auebds.coffeui.ui.drinks.french.CreateFrenchActivity;
import com.auebds.coffeui.ui.drinks.tea.CreateTeaActivity;
import com.auebds.coffeui.ui.schedule.manage.ManageScheduleActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {
    public static final String ARG_MESSAGE = "MESSAGE";
    private static final int SNACKBAR_DURATION = BaseTransientBottomBar.LENGTH_SHORT;

    private ActivityMainMenuBinding binding;

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());

        View rootView = binding.getRoot();
        setContentView(rootView);

        LocalDate today = LocalDate.now();
        String month = capitalize(today.getMonth().toString());
        binding.textDate.setText(String.format(Locale.ROOT, "%s %d", month, today.getDayOfMonth()));

        // on click exit application
        binding.offButton.setOnClickListener(view -> this.finishAndRemoveTask());

        SettingsDao settings = SettingsDao.getInstance(getBaseContext());
        settings.isVoiceOn().subscribe(this::setSoundIcon);
        binding.soundButton.setOnClickListener(v -> settings.switchVoice());

        Intent chocolateIntent = new Intent(MainMenuActivity.this, CreateChocolateActivity.class);
        ActivityResultLauncher<Void> chocolateLauncher = getDrinkLauncher(rootView, chocolateIntent);

        Intent frenchIntent = new Intent(MainMenuActivity.this, CreateFrenchActivity.class);
        ActivityResultLauncher<Void> frenchLauncher = getDrinkLauncher(rootView, frenchIntent);

        Intent teaIntent = new Intent(MainMenuActivity.this, CreateTeaActivity.class);
        ActivityResultLauncher<Void> teaLauncher = getDrinkLauncher(rootView, teaIntent);

        Intent espressoIntent = new Intent(MainMenuActivity.this, CreateEspressoActivity.class);
        ActivityResultLauncher<Void> espressoLauncher = getDrinkLauncher(rootView, espressoIntent);

        binding.schedulesButton.setOnClickListener(view -> {
            // later switch this for schedule management activity
            Intent intent = new Intent(MainMenuActivity.this, ManageScheduleActivity.class);
            startActivity(intent);
        });

        binding.chocolateButton.setOnClickListener(view -> chocolateLauncher.launch(null));

        binding.frenchButton.setOnClickListener(view -> frenchLauncher.launch(null));

        binding.teaButton.setOnClickListener(view -> teaLauncher.launch(null));

        binding.espressoButton.setOnClickListener(view ->espressoLauncher.launch(null));
    }

    /**
     * A contract where a drink activity will return a success message to the main menu when
     * successfully completed.
     * @param rootView the root view of the main menu
     * @param intent the intent which will start the next activity
     * @return the launcher which will exeucute the startActivityForResult call
     */
    private ActivityResultLauncher<Void> getDrinkLauncher(View rootView, Intent intent) {

        ActivityResultContract<Void, String> contract = new ActivityResultContract<Void, String>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Void unused) {
                return intent;
            }

            @Override
            public String parseResult(int resultCode, @Nullable Intent result) {
                if (resultCode != Activity.RESULT_OK || result == null) {
                    return null;
                }
                return result.getStringExtra(ARG_MESSAGE);
            }
        };

        ActivityResultCallback<String> callback = message -> {
            if(message != null) {
                Snackbar.make(rootView, message, SNACKBAR_DURATION).show();
            }
        };

        return registerForActivityResult(contract, callback);
    }

    private void setSoundIcon(boolean voiceIsActivated) {
        runOnUiThread(() -> {
            if(voiceIsActivated) {
                binding.soundButton.setImageResource(R.drawable.sound_on);
            } else {
                binding.soundButton.setImageResource(R.drawable.sound_off);
            }
        });
    }

    private String capitalize(String str){
        if(str.isEmpty()) // avoid IndexOutOfBounds exception
            return str;
        else
            return str.substring(0, 1).toUpperCase(Locale.ROOT) +
                    str.substring(1).toLowerCase(Locale.ROOT);
    }
}