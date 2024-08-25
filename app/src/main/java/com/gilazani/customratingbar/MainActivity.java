package com.gilazani.customratingbar;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gilazani.customratingbarmodule.CustomRatingBar;
import com.gilazani.customratingbarmodule.OnRatingChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CustomRatingBar customRatingBar = findViewById(R.id.customRatingBar);

        customRatingBar.setOnRatingChangeListener(new OnRatingChangeListener() {
            @Override
            public void onRatingChanged(int newRating) {
                Toast.makeText(MainActivity.this, "New Rating: " + newRating, Toast.LENGTH_SHORT).show();
            }
        });

    }
}