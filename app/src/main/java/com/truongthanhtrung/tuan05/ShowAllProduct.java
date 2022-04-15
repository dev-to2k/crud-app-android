package com.truongthanhtrung.tuan05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowAllProduct extends AppCompatActivity {
    public static final String URL_SHOW_ALL_PROD = "http://192.168.0.122/api/get_all_product.php";
    ArrayList<Product> data;
    ListView lvprod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_product);
    }

    public void show_all_prod(View view) {
        class show_all_prod extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                return requestHandler.sendPostRequest(URL_SHOW_ALL_PROD, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    if (s.isEmpty()) {
                        Toast.makeText(ShowAllProduct.this, "S khong co gia tri !!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        JSONArray jo = obj.getJSONArray("alldata");
                        data = new ArrayList<Product>();

                        for (int i = 0; i < jo.length(); i++) {
                            data.add(new Product(jo.getJSONObject(i).getString("name"),
                                    jo.getJSONObject(i).getString("price"),
                                    jo.getJSONObject(i).getString("description"),
                                    jo.getJSONObject(i).getString("color")));
                        }
                        ProductAdapter adapter = new ProductAdapter(ShowAllProduct.this, R.layout.layout, data);
                        lvprod = findViewById(R.id.list);
                        lvprod.setAdapter(adapter);
                        Toast.makeText(ShowAllProduct.this, jo.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ShowAllProduct.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        show_all_prod show = new show_all_prod();
        show.execute();
    }
}