package com.example.doordashapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doordashapp.R;
import com.example.doordashapp.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private Context mContext;
    private int mResource;
    //private List<Restaurant> restaurantList;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView name;
        TextView description;
        ImageView coverImage;
        TextView duration;
    }

    public RestaurantAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        String description = getItem(position).getDescription();
        String duration = getItem(position).getStatus();
        String coverImageUrl = getItem(position).getCover_img_url();

        //Create Restaurant object with this information
        Restaurant rest = new Restaurant(name, coverImageUrl, description, duration);

        final View result;
        ViewHolder holder;

        holder = new ViewHolder();

        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent,false);
            holder.name = convertView.findViewById(R.id.textView_name);
            holder.description = convertView.findViewById(R.id.textView_description);
            holder.duration = convertView.findViewById(R.id.textView_status);
            holder.coverImage = convertView.findViewById(R.id.imageView_cover_image);

            result = convertView;
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(rest.getName());
        holder.description.setText(rest.getDescription());
        holder.duration.setText(rest.getStatus());
        Picasso.get().load(rest.getCover_img_url()).into(holder.coverImage);

        return convertView;
/*
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Restaurant current = this.restaurantList.get(position);

        ImageView image = listItem.findViewById(R.id.imageView_coverimage);
        Picasso.get().load(current.getCover_img_url()).into(image);

        TextView name = listItem.findViewById(R.id.textView_name);
        name.setText(current.getName());

        //TextView desc = listItem.findViewById(R.id.textView_description);
        //desc.setText(current.getDescription());

        //TextView status = listItem.findViewById(R.id.textView_status);
        //status.setText(current.getStatus());


        return listItem;
        */
    }
}
