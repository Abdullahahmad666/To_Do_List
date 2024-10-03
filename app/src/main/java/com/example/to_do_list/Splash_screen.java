package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Splash_screen extends AppCompatActivity {

    private Animation logo_animation;
    private ImageView ivLogo;

    // Create an ActivityResultLauncher
    private final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                // Handle the result if needed
                if (result.getResultCode() == RESULT_OK) {
                    // Future handling of the result can be done here
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize views and animations
        init();

        // Start the animation
        ivLogo.startAnimation(logo_animation);

        // Use Handler to delay navigation
        new Handler().postDelayed(() -> {
            // Start the next activity with ActivityResultLauncher
            Intent intent = new Intent(Splash_screen.this, MainActivity.class);
            activityResultLauncher.launch(intent);
            finish();  // Close the splash activity
        }, 5000);  // Duration of splash screen
    }

    private void init() {
        logo_animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        ivLogo = findViewById(R.id.ivlogo);
    }
}
