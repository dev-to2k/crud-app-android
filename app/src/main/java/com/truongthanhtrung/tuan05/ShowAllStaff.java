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

public class ShowAllStaff extends AppCompatActivity {
    public static final String URL_SHOW_ALL_STAFF = "http://192.168.43.62/api/get_all_staff.php";
    ArrayList<Staff> data;
    ListView lvprod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_staff);
    }

    public void show_all_staff(View view) {
        class show_all_staff extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                return requestHandler.sendPostRequest(URL_SHOW_ALL_STAFF, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    if (s.isEmpty()) {
                        Toast.makeText(ShowAllStaff.this, "S khong co gia tri !!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        JSONArray jo = obj.getJSONArray("alldata");
                        data = new ArrayList<Staff>();

                        for (int i = 0; i < jo.length(); i++) {
                            data.add(new Staff(jo.getJSONObject(i).getString("hotennv"),
                                    jo.getJSONObject(i).getString("ngaysinh"),
                                    jo.getJSONObject(i).getString("gioitinh"),
                                    jo.getJSONObject(i).getString("noisinh"),
                                    jo.getJSONObject(i).getString("luong")));
                        }
                        StaffAdapter adapter = new StaffAdapter(ShowAllStaff.this, R.layout.layout, data);
                        lvprod = findViewById(R.id.list);
                        lvprod.setAdapter(adapter);
                        Toast.makeText(ShowAllStaff.this, jo.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ShowAllStaff.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        show_all_staff show = new show_all_staff();
        show.execute();
    }
}