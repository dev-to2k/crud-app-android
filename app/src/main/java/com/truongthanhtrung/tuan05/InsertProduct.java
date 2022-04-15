package com.truongthanhtrung.tuan05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InsertProduct extends AppCompatActivity {
    public static final String URL_ADD_PROD = "http://192.168.0.122/api/create_product.php";
    private EditText etName;
    private EditText etPrice;
    private EditText etDesc;
    private RadioGroup radioGroup;
    private RadioButton radioColorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        etName = findViewById(R.id.name);
        etPrice = findViewById(R.id.price);
        etDesc = findViewById(R.id.desc);
        radioGroup = findViewById(R.id.color_group);
    }

    public void add_prod(View view) {
        final String name = etName.getText().toString();
        final String price = etPrice.getText().toString();
        final String desc = etDesc.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioColorButton = findViewById(selectedId);

        final String color = radioColorButton.getText().toString();

        class Product extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(InsertProduct.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("price", price);
                params.put("description", desc);
                params.put("color", color);

                return requestHandler.sendPostRequest(URL_ADD_PROD, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    System.out.println(obj);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(InsertProduct.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        Product prod_exec = new Product();
        prod_exec.execute();
    }
}