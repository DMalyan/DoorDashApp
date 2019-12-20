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

    //region FIELDS
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    private List<Restaurant> mRestaurantList;
    //endregion

    //region CONSTRUCTORS
    public RestaurantAdapter(@NonNull Context context, int resource, List<Restaurant> list) {
        super(context, R.layout.list_item, list);
        mContext = context;
        //  mResource = resource;
        mRestaurantList = list;
    }
    //endregion

    //region SETTERS and GETTERS
    //endregion

    //region PUBLIC
    //endregion

    //region PRIVATE/PROTECTED
    //endregion

    //region OVERRIDDEN

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
            viewHolder.id=(String)convertView.findViewById(R.id.textView_name).getTag();
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
        viewHolder.id=current.getId();


        return convertView;
    }
    //endregion


    private static class ViewHolder {
        TextView name;
        TextView description;
        ImageView coverImage;
        TextView duration;
        String id;
    }
}
