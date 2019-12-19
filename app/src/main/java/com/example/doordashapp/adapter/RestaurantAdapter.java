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
    private int lastPosition = -1;
    private List<Restaurant> mRestaurantList;

    private static class ViewHolder {
        TextView name;
        TextView description;
        ImageView coverImage;
        TextView duration;
    }

    public RestaurantAdapter(@NonNull Context context, int resource, List<Restaurant> list) {
        super(context, R.layout.list_item, list);
        mContext = context;
      //  mResource = resource;
        mRestaurantList = list;
    }
/*
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Restaurant current = mRestaurantList.get(position);

        ImageView image = listItem.findViewById(R.id.imageView_cover_image);
        String imageUri = current.getCover_img_url();
        Picasso.get().load(imageUri).into(image);

        TextView name = listItem.findViewById(R.id.textView_name);
        name.setText(current.getName());

        TextView desc = listItem.findViewById(R.id.textView_description);
        desc.setText(current.getDescription());

        TextView status = listItem.findViewById(R.id.textView_status);
        status.setText(current.getStatus());

        return listItem;
    }
*/

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Restaurant current = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name = convertView.findViewById(R.id.textView_name);
            viewHolder.description = convertView.findViewById(R.id.textView_description);
            viewHolder.duration = convertView.findViewById(R.id.textView_status);
            viewHolder.coverImage = convertView.findViewById(R.id.imageView_cover_image);

            convertView.setTag(viewHolder);
            result = convertView;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_up_anim : R.anim.load_down_anim);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.name.setText(current.getName());
        viewHolder.description.setText(current.getDescription());
        viewHolder.duration.setText(current.getStatus());
        Picasso.get().load(current.getCover_img_url()).into(viewHolder.coverImage);

        return convertView;
    }
}
