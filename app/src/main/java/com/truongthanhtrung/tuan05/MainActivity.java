package com.truongthanhtrung.tuan05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInsertProduct;
    Button btnShowAllProduct;
    Button btnShowProductDetails;
    Button btnUpdatePriceProduct;
    Button btnDeleteProduct;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertProduct = findViewById(R.id.btnInsertProduct);
        btnInsertProduct.setOnClickListener(this);

        btnShowAllProduct = findViewById(R.id.btnShowAllProduct);
        btnShowAllProduct.setOnClickListener(this);

        btnShowProductDetails = findViewById(R.id.btnShowProductDetails);
        btnShowProductDetails.setOnClickListener(this);

        btnUpdatePriceProduct = findViewById(R.id.btnUpdatePriceProduct);
        btnUpdatePriceProduct.setOnClickListener(this);

        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);
        btnDeleteProduct.setOnClickListener(this);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsertProduct: {
                Intent i = new Intent(MainActivity.this, InsertStaff.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnShowAllProduct: {
                Intent i = new Intent(MainActivity.this, ShowAllStaff.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnShowProductDetails: {
                Intent i = new Intent(MainActivity.this, ShowStaffDetails.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnUpdatePriceProduct: {
                Intent i = new Intent(MainActivity.this, UpdateSalaryStaff.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnDeleteProduct: {
                Intent i = new Intent(MainActivity.this, DeleteStaff.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnExit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }

}