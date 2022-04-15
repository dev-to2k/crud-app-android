package com.truongthanhtrung.tuan05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class UpdatePriceProduct extends AppCompatActivity {
    public static final String URL_UPDATE_PROD = "http://192.168.0.122/api/update_price.php";
    private EditText etIdUpdate, etPriceUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_price_product);
        etIdUpdate = findViewById(R.id.id_update);
        etPriceUpdate = findViewById(R.id.price_update);
    }

    public void update_price(View view) {
        final String id = etIdUpdate.getText().toString();
        final String price = etPriceUpdate.getText().toString();

        class Update extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(UpdatePriceProduct.this);

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
                params.put("id", id);
                params.put("price", price);

                return requestHandler.sendPostRequest(URL_UPDATE_PROD, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(UpdatePriceProduct.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(UpdatePriceProduct.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        Update update = new Update();
        update.execute();
    }

}