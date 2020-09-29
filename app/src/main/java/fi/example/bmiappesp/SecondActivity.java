package fi.example.bmiappesp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;

public class SecondActivity extends AppCompatActivity {

    TextView tvResult;
    TextView lbLabel;
    ImageView image;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvResult = (TextView) findViewById(R.id.resultView);
        lbLabel= (TextView) findViewById(R.id.labelView);
        image = (ImageView) findViewById(R.id.imageView2);

        Intent in = getIntent();
        Float result = in.getFloatExtra("key1", 0);
        Float heightV = in.getFloatExtra("pit", 0);
        Float weightV = in.getFloatExtra("pai", 0);
        DecimalFormat df = new DecimalFormat("###.#");

        String label ="";
        double minWeight = 18.5* (heightV*heightV);
        double maxWeight = 25* (heightV*heightV);
        double maxD = weightV - maxWeight;
        double minD = minWeight - weightV;

        int idImage = getResources().getIdentifier("norm", "drawable", getOpPackageName());

        if (result < 18.5){
            label =  "\n" + " " + "\n\n" + "Normaali painosi tulisi olla: " + "\n" + df.format(minWeight) + " - " + df.format(maxWeight) + " kg." + "\n\n" + "Normaalipainoon - "+ df.format(minD) + " kg.";
            //Olet alipainoinen.
            image.setImageResource(R.drawable.alip);
        }
        else if (result >= 18.5 && result <=25){
            label = "\n" + "Olet normaalipainoinen.";
            //image.setImageResource(R.drawable.norm);
            image.setImageResource(idImage);

        }
        else if (result >18.5 && result <=30){
            label = "\n" + "Olet ylipainoinen." + "\n\n" + "Normaali painosi tulisi olla: " + "\n" + df.format(minWeight) + " - " + df.format(maxWeight) + " kg." + "\n\n" + "Normaalipainoon  + "+ df.format(maxD) + " kg.";
            image.setImageResource(R.drawable.ylip);
        }

        else if (result >30){
            label = "\n" + "Olet merkittävästi lihava." + "\n\n" + "Normaali painosi tulisi olla: " + "\n" + df.format(minWeight) + " - " + df.format(maxWeight) + " kg." + "\n\n" + "Normaalipainoon  + "+ df.format(maxD) + " kg.";
            image.setImageResource(R.drawable.sairaal);
        }

        tvResult.setText(df.format(result));
        lbLabel.setText(label);
    }
}
