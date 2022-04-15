package com.truongthanhtrung.tuan05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StaffAdapter extends BaseAdapter {
    Context context;
    List<Staff> data;
    int layoutResourceId;

    public StaffAdapter(Context context, int layoutResourceId, List<Staff> data) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public int getCount() {
        if(data.size() != 0 && !data.isEmpty()) {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        StaffHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
            holder = new StaffHolder();
            holder.txtFullName = convertView.findViewById(R.id.txtFullName);
            holder.txtDob = convertView.findViewById(R.id.txtDob);
            holder.txtBirthPlace = convertView.findViewById(R.id.txtBirthPlace);
            holder.txtSalary = convertView.findViewById(R.id.txtSalary);
            holder.txtGender = convertView.findViewById(R.id.txtGender);
            convertView.setTag(holder);
        } else {
            holder = (StaffHolder) convertView.getTag();
        }

        Staff pf = data.get(i);
        holder.txtFullName.setText(pf.getFullName());
        holder.txtDob.setText(pf.getDob());
        holder.txtBirthPlace.setText(pf.getBirthPlace());
        holder.txtSalary.setText(pf.getSalary());
        holder.txtGender.setText(pf.getGender());

        return convertView;
    }

    class StaffHolder {
        TextView txtFullName;
        TextView txtDob;
        TextView txtBirthPlace;
        TextView txtSalary;
        TextView txtGender;
    }
}
