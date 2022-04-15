package com.truongthanhtrung.tuan05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInsertProduct;
    Button btnShowAllProduct;
    Button btnShowProductDetails;
    Button btnUpdatePriceProduct;
    Button btnDeleteProduct;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsertProduct: {
                Intent i = new Intent(MainActivity.this, InsertProduct.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnShowAllProduct: {
                Intent i = new Intent(MainActivity.this, ShowAllProduct.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnShowProductDetails: {
                Intent i = new Intent(MainActivity.this, ShowProductDetails.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnUpdatePriceProduct: {
                Intent i = new Intent(MainActivity.this, UpdatePriceProduct.class);
                v.getContext().startActivity(i);
                break;
            }
            case R.id.btnDeleteProduct: {
                Intent i = new Intent(MainActivity.this, DeleteProduct.class);
                v.getContext().startActivity(i);
                break;
            }
            default:
                break;
        }
    }

}