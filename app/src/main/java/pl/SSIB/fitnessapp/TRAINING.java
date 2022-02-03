package pl.SSIB.fitnessapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class TRAINING extends AppCompatActivity implements SensorEventListener {
    private Button button2;
    private int x = 1;
    private int y = 0;
    private AlertDialog.Builder Kalibracja;
    private AlertDialog.Builder Gratulacje;
    private AlertDialog dialog;
    private AlertDialog dialog2;
    private Button ok;
    private Button ok2;
    private float lastZ;
    private int ceel1;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float maxZ = -100;
    private Button button12, button13;
    private float deltaZ = 0;
    private float prevDeltaZ;
    private float zliczanie;
    private float vibrateThreshold = 0;

    private TextView currentZ, cnt;

    public Vibrator v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDialog();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_r_a_i_n_i_n_g);
        initializeViews();

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMAIN();
            }
        });
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        } else {
        }

        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static void main(String args[]) {
        try {
            TRAINING obj = new TRAINING();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run(String[] args) throws Exception {
        System.out.print(zliczanie);
    }

    public void initializeViews() {
        currentZ = (TextView) findViewById(R.id.currentZ);
        cnt = (TextView) findViewById(R.id.cnt);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void openMAIN() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        displayCleanValues();
        displayCurrentValues();

        deltaZ = lastZ + event.values[2];


        if (Math.abs(deltaZ) >= maxZ - 2 && Math.abs(prevDeltaZ) < maxZ && x == 1) {
            zliczanie += 0.5;
        }
        if (ceel1==zliczanie && ceel1>0){
            Gratulacje();
        }
        prevDeltaZ = deltaZ;
        if (x == 0)
            kalibracja();
    }

    private void kalibracja() {
        float tempMaxZ = Math.max(Math.abs((int) deltaZ), Math.abs((int) prevDeltaZ));
        if (tempMaxZ > maxZ) maxZ = tempMaxZ;
        System.out.println(maxZ);
    }

    private void displayCurrentValues() {
        currentZ.setText(Float.toString(deltaZ));
        cnt.setText(Integer.toString((int) zliczanie));
        // System.out.println(zliczanie);
    }


    private void displayCleanValues() {
        currentZ.setText("0.0");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void Kalibracja1() {
        Kalibracja = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);

        ok = contactPopupView.findViewById(R.id.ok);
        Kalibracja.setView(contactPopupView);
        dialog = Kalibracja.create();
        dialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                x = 1;
            }
        });
    }

    public void openDialog() {
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_cel_przysiady, null);
        NumberPicker numberPicker1 = (NumberPicker) linearLayout.findViewById(R.id.numberPicker1);

        numberPicker1.setMinValue(1);
        numberPicker1.setMaxValue(100);
        numberPicker1.setValue(5);
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setCancelable(false)
                .create();
        builder.show();

        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                ceel1 = newVal;
                System.out.println(ceel1);
            }
        });
        button12 = linearLayout.findViewById(R.id.button12);
         button12.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openMAIN();
             }
         });
        button13 = linearLayout.findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
                Kalibracja1();
                x=0;
            }
        });

    }
    public void Gratulacje() {

        Gratulacje = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.activity_gratulacje, null);

        ok2 = contactPopupView.findViewById(R.id.ok2);
        Gratulacje.setView(contactPopupView);
        dialog2 = Gratulacje.create();
        dialog2.show();
        Vibrator vibrator;
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);

        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
                openMAIN();
            }
        });
    }
}
