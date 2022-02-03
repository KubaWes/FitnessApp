package pl.SSIB.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button3 =findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPOMPKI();
            }
        });
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openTRAINING();
            }
        });
    }

    public void openTRAINING(){
        Intent intent = new Intent(this, TRAINING.class);
        startActivity(intent);
    }

    public void openPOMPKI(){
        Intent intent = new Intent(this, POMPKI.class);
        startActivity(intent);
    }
}
