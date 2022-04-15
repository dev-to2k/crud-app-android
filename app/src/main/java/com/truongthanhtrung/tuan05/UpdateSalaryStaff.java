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

public class UpdateSalaryStaff extends AppCompatActivity {
    public static final String URL_UPDATE_STAFF = "http://192.168.43.62/api/update_salary.php";
    private EditText etIdUpdate, etPriceUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_salary_staff);
        etIdUpdate = findViewById(R.id.id_update);
        etPriceUpdate = findViewById(R.id.price_update);
    }

    public void update_salary(View view) {
        final String id = etIdUpdate.getText().toString();
        final String salary = etPriceUpdate.getText().toString();

        class Update extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(UpdateSalaryStaff.this);

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
                params.put("manv", id);
                params.put("luong", salary);

                return requestHandler.sendPostRequest(URL_UPDATE_STAFF, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(UpdateSalaryStaff.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(UpdateSalaryStaff.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        Update update = new Update();
        update.execute();
    }

}