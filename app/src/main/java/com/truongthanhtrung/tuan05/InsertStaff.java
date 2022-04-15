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

public class InsertStaff extends AppCompatActivity {
    public static final String URL_ADD_STAFF = "http://192.168.43.62/api/create_staff.php";
    private EditText etFullName;
    private EditText etDob;
    private EditText etBirthPlace;
    private EditText etSalary;
    private RadioGroup radioGroup;
    private RadioButton radioGenderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_staff);
        etFullName = findViewById(R.id.fullName);
        etDob = findViewById(R.id.dob);
        etBirthPlace = findViewById(R.id.birthPlace);
        etSalary = findViewById(R.id.salary);
        radioGroup = findViewById(R.id.gender_group);
    }

    public void add_staff(View view) {
        final String fullName = etFullName.getText().toString();
        final String dob = etDob.getText().toString();
        final String birthPlace = etBirthPlace.getText().toString();
        final String salary = etSalary.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioGenderButton = findViewById(selectedId);

        final String gender = radioGenderButton.getText().toString();

        class Staff extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(InsertStaff.this);

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
                params.put("hotennv", fullName);
                params.put("ngaysinh", dob);
                params.put("noisinh", birthPlace);
                params.put("luong", salary);
                params.put("gioitinh", gender);

                return requestHandler.sendPostRequest(URL_ADD_STAFF, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(InsertStaff.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        Staff staff_exec = new Staff();
        staff_exec.execute();
    }
}