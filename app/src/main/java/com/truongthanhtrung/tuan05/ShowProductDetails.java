package com.truongthanhtrung.tuan05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ShowProductDetails extends AppCompatActivity {
    public static final String URL_SHOW_PROD = "http://192.168.0.122/api/get_product_details.php";
    private EditText etId;
    private TextView tvName, tvPrice, tvDesc, tvColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_details);
        etId = findViewById(R.id.id);
        tvName = findViewById(R.id.showname);
        tvPrice = findViewById(R.id.showprice);
        tvDesc = findViewById(R.id.showdesc);
        tvColor = findViewById(R.id.showcolor);
    }


    public void show_prod(View view) {
        final String id = etId.getText().toString();

        class show_prod extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ShowProductDetails.this);

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

                return requestHandler.sendPostRequest(URL_SHOW_PROD, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(ShowProductDetails.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                        tvName.setVisibility(View.VISIBLE);
                        tvPrice.setVisibility(View.VISIBLE);
                        tvDesc.setVisibility(View.VISIBLE);
                        tvColor.setVisibility(View.VISIBLE);

                        tvName.setText("Name: " + obj.getString("name"));
                        tvPrice.setText("Price: " + obj.getString("price"));
                        tvDesc.setText("Desc: " + obj.getString("description"));
                        tvColor.setText("Color: " + obj.getString("color"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ShowProductDetails.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        show_prod show = new show_prod();
        show.execute();
    }
}