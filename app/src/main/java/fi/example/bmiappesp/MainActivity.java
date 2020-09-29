package fi.example.bmiappesp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText h;
    private EditText w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = (EditText) findViewById(R.id.editPituus);
        w = (EditText) findViewById(R.id.editPaino);
    }

    public void calculate(View v) {
        String height = h.getText().toString();
        String weight = w.getText().toString();

        if ((TextUtils.isEmpty(height)) && (TextUtils.isEmpty(weight))) {
            h.setError("Kirjoita pituus!");
            w.setError("Kirjoita paino!");
            return;
        }

        else if (TextUtils.isEmpty(height)) {
            h.setError("Kirjoita pituus!");
            h.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(weight)) {
            w.setError("Kirjoita paino!");
            w.requestFocus();
            return;
        }

        Float hValue = Float.parseFloat(height) / 100;
        Float wValue = Float.parseFloat(weight);

        if (hValue < 1.29 || hValue > 2.30) {
            h.setError("130 - 230");
            h.requestFocus();
        }

        else if (wValue < 30 || wValue > 220) {
            w.setError("30 - 220");
            w.requestFocus();
        }

        else {
            Float bmi = wValue / (hValue * hValue);
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("key1", bmi);
            intent.putExtra("pit", hValue);
            intent.putExtra("pai", wValue);
            startActivity(intent);
        }
    }
}
