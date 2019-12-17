package com.example.doordashapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doordashapp.R;
import com.example.doordashapp.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private Context mContext;
    private List<Restaurant> restaurantList;

    public RestaurantAdapter(@NonNull Context context, ArrayList<Restaurant> list) {
        super(context, 0 , list);
        mContext = context;
        restaurantList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Restaurant current = this.restaurantList.get(position);

        ImageView image = listItem.findViewById(R.id.imageView_coverimage);
        Picasso.get().load(current.getCover_img_url()).into(image);

        TextView name = listItem.findViewById(R.id.textView_name);
        name.setText(current.getName());

        TextView desc = listItem.findViewById(R.id.textView_description);
        desc.setText(current.getDescription());

        TextView status = listItem.findViewById(R.id.textView_status);
        status.setText(current.getStatus());

        return listItem;
    }
}
