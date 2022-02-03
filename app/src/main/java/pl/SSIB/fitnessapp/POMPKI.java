package pl.SSIB.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.health.SystemHealthManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class POMPKI extends AppCompatActivity {
    public int ceel;
    private AlertDialog.Builder Gratulacje3;
    private AlertDialog dialog3;
    private Button ok3;
    private Button button4, button14, button15;
    private Button touch;
    private TextView textView5;
    private int licznik1=0;
    private AlertDialog.Builder Cel;
    private AlertDialog dialog1;
    private Button okA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_o_m_p_k_i);
        licznik1=0;
        final TextView licznikText = findViewById(R.id.textView5);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMAIN();
            }
        });
        System.out.println("Przy otwarciu");

        touch = findViewById(R.id.touch);
        touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(POMPKI.this, "Button Clicked", Toast.LENGTH_LONG).show();

                licznik1+=1;
                System.out.println(licznik1);
                licznikText.setText(Integer.toString(licznik1));
                System.out.println(ceel);
                if (licznik1==ceel){ Gratulacje3();}

            }
        });
    }
        public void openMAIN(){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            button4 = findViewById(R.id.button4);
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMAIN();
                }
            });


        }
    public void openDialog(){
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_cel, null);
        NumberPicker numberPicker = (NumberPicker) linearLayout.findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        numberPicker.setValue(5);
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setCancelable(false)
                .create();
        builder.show();

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                ceel=newVal;
                System.out.println(ceel);
            }
        });
        button14 = linearLayout.findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMAIN();
            }
        });
        button15 = linearLayout.findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });

    }
    public void Gratulacje3() {
        Vibrator vibrator;
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
        Gratulacje3 = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.activity_gratulacje3, null);

        ok3 = contactPopupView.findViewById(R.id.ok3);
        Gratulacje3.setView(contactPopupView);
        dialog3 = Gratulacje3.create();
        dialog3.show();

        ok3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.dismiss();
                openMAIN();
            }
        });
    }
}
