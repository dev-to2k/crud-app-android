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

public class DeleteStaff extends AppCompatActivity {
    public static final String URL_DELETE_STAFF = "http://192.168.43.62/api/delete_staff.php";
    private EditText etIdDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_staff);
        etIdDelete = findViewById(R.id.id_delete);

    }

    public void delete_staff(View view) {
        final String id = etIdDelete.getText().toString();

        class Delete extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(DeleteStaff.this);

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

                return requestHandler.sendPostRequest(URL_DELETE_STAFF, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(DeleteStaff.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DeleteStaff.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        Delete delete = new Delete();
        delete.execute();
    }
}