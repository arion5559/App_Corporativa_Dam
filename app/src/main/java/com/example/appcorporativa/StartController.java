package com.example.appcorporativa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartController extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        ImageView imageView = findViewById(R.id.start);

        imageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                Intent intent = new Intent(StartController.this, LogInController.class);
                startActivity(intent);
            }
        });
    }
}
