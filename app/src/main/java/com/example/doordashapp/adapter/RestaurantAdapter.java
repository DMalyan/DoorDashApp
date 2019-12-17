package com.example.doordashapp.adapter;

import android.content.Context;
import android.net.Uri;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private Context mContext;
    private List<Restaurant> restaurantList;

    public RestaurantAdapter(@NonNull Context context, @LayoutRes ArrayList<Restaurant> list) {
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
        //image.setImageResource(current.getCover_image_url());
        String imageUri = current.getCover_img_url();
                //current.getCover_image_url();
                //"https://i.imgur.com/tGbaZCY.jpg";
        Picasso.get().load(imageUri).into(image);

        TextView name = listItem.findViewById(R.id.textView_name);
        name.setText(current.getName());

        TextView desc = listItem.findViewById(R.id.textView_description);
        desc.setText(current.getDescription());

        TextView status = listItem.findViewById(R.id.textView_status);
        status.setText(current.getStatus());

        return listItem;
    }
}
