package com.example.mybmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListensers();
    }

    private Button but_calc;
    private EditText num_h;
    private EditText num_w;
    private TextView show_r;
    private TextView show_s;
    private ImageView show_p;

    private void initViews(){
        but_calc = (Button)findViewById(R.id.button);
        num_h = (EditText)findViewById(R.id.height);
        num_w = (EditText)findViewById(R.id.weight);
        show_r = (TextView)findViewById(R.id.reuslt);
        show_s = (TextView)findViewById(R.id.suggest);
        show_p = (ImageView)findViewById(R.id.photo);
    }

    private void setListensers(){
        but_calc.setOnClickListener(calcBMI);
    }

    private View.OnClickListener calcBMI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                DecimalFormat nf = new DecimalFormat("0.00");

                double h = Double.parseDouble(num_h.getText().toString()) / 100;
                double w = Double.parseDouble(num_w.getText().toString());
                double BMI = w / (h * h);

                show_r.setText(getText(R.string.bmi_result) + nf.format(BMI));//0123

                if (BMI > 25)
                    show_s.setText(R.string.bmi_fat);
                else if (BMI < 20)
                    show_s.setText(R.string.bmi_light);
                else
                    show_s.setText(R.string.bmi_normal);

                if (BMI > 25)
                    show_p.setImageResource(R.drawable._98848099_4177283722360021_3058474877984671774_n);
                else if (BMI < 20)
                    show_p.setImageResource(R.drawable.fbk_1);
                else
                    show_p.setImageResource(R.drawable.towa);
            }
            catch(Exception obj)
            {
                Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                openOptionsDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openOptionsDialog(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.tittle)
                .setMessage(R.string.message)
                .setPositiveButton(R.string.Ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .show();
    }
}