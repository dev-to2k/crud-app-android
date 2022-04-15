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

public class ShowStaffDetails extends AppCompatActivity {
    public static final String URL_SHOW_STAFF = "http://192.168.43.62/api/get_staff_details.php";
    private EditText etId;
    private TextView tvFullName, tvDob, tvBirthPlace, tvSalary, tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_staff_details);
        etId = findViewById(R.id.id);
        tvFullName = findViewById(R.id.showfullname);
        tvDob = findViewById(R.id.showdob);
        tvBirthPlace = findViewById(R.id.showbirthplace);
        tvSalary = findViewById(R.id.showsalary);
        tvGender = findViewById(R.id.showgender);
    }


    public void show_staff(View view) {
        final String id = etId.getText().toString();

        class show_staff extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ShowStaffDetails.this);

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

                return requestHandler.sendPostRequest(URL_SHOW_STAFF, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(ShowStaffDetails.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                        tvFullName.setVisibility(View.VISIBLE);
                        tvDob.setVisibility(View.VISIBLE);
                        tvBirthPlace.setVisibility(View.VISIBLE);
                        tvSalary.setVisibility(View.VISIBLE);
                        tvGender.setVisibility(View.VISIBLE);

                        tvFullName.setText("Full name: " + obj.getString("hotennv"));
                        tvDob.setText("Dob: " + obj.getString("ngaysinh"));
                        tvBirthPlace.setText("Birth place: " + obj.getString("noisinh"));
                        tvSalary.setText("Salary: " + obj.getString("luong"));
                        tvGender.setText("Gender: " + obj.getString("gioitinh"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ShowStaffDetails.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        show_staff show = new show_staff();
        show.execute();
    }
}