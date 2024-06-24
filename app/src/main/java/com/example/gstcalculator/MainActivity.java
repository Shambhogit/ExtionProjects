package com.example.gstcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnCalculateGST, btnClearAll;
    EditText edtAmount;
    TextView tvTotal, tvGST;
    RadioGroup rgTax;
    float gst;
    float amount;
    RadioButton rb_0_25, rb_3, rb_5, rb_12, rb_18, rb_28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCalculateGST = findViewById(R.id.btn_calculate_gst);
        edtAmount = findViewById(R.id.et_amount);
        tvGST = findViewById(R.id.tv_gst);
        tvTotal = findViewById(R.id.tv_total);
        rgTax = findViewById(R.id.rg_tax_rate);
        btnClearAll = findViewById(R.id.btn_clear_all);

        rb_0_25 = findViewById(R.id.rb_0_25);
        rb_3 = findViewById(R.id.rb_3);
        rb_5 = findViewById(R.id.rb_5);
        rb_12 = findViewById(R.id.rb_12);
        rb_18 = findViewById(R.id.rb_18);
        rb_28 = findViewById(R.id.rb_28);

        gst = 0;


        rgTax.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(findViewById(checkedId) == rb_0_25 ){
                    gst = 0.25f;
                }else if(findViewById(checkedId) == rb_3){
                    gst = 3.0f;
                }else if(findViewById(checkedId) == rb_5){
                    gst = 5.0f;
                }else if(findViewById(checkedId) == rb_12){
                    gst = 12.0f;
                }else if(findViewById(checkedId) == rb_18){
                    gst = 18.0f;
                }else if(findViewById(checkedId) == rb_28){
                    gst = 28.0f;
                }
            }
        });


        btnCalculateGST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = edtAmount.getText().toString();

                if(txt.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else if(gst == 0){
                    Toast.makeText(MainActivity.this, "Please Select Tax rate", Toast.LENGTH_SHORT).show();
                }else{
                    amount = Float.parseFloat(txt);
                    float tax = ((amount * gst)/100);
                    float total = amount + tax;


                    tvTotal.setText(Float.toString(total));
                    tvGST.setText(Float.toString(tax));
                }
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb_0_25.setChecked(false);
                rb_3.setChecked(false);
                rb_5.setChecked(false);
                rb_12.setChecked(false);
                rb_18.setChecked(false);
                rb_28.setChecked(false);
                edtAmount.setText("");
                tvGST.setText("Nill");
                tvTotal.setText("Nill");
            }
        });
    }
}