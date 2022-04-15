package com.truongthanhtrung.tuan05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Context context;
    List<Product> data;
    int layoutResourceId;

    public ProductAdapter(Context context, int layoutResourceId, List<Product> data) {
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
        ProductHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
            holder = new ProductHolder();
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtPrice = convertView.findViewById(R.id.txtPrice);
            holder.txtDesc = convertView.findViewById(R.id.txtDesc);
            holder.txtColor = convertView.findViewById(R.id.txtColor);
            convertView.setTag(holder);
        } else {
            holder = (ProductHolder) convertView.getTag();
        }

        Product pf = data.get(i);
        holder.txtName.setText(pf.getName());
        holder.txtPrice.setText(pf.getPrice());
        holder.txtDesc.setText(pf.getDesc());
        holder.txtColor.setText(pf.getColor());

        return convertView;
    }

    class ProductHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtDesc;
        TextView txtColor;
    }
}
