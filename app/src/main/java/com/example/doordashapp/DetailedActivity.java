package com.example.doordashapp;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doordashapp.model.ModelCallback;
import com.example.doordashapp.model.ModelProvider;
import com.example.doordashapp.model.Restaurant;
import com.squareup.picasso.Picasso;


public class DetailedActivity extends AppCompatActivity {

    private Context mContext;
    private ModelProvider mProvider;
    private int mRestaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detailed_activity);

        mContext = this;
        mProvider = new ModelProvider();

        String newString;
            if (savedInstanceState == null) {
                Bundle extras = getIntent().getExtras();
                if(extras == null) {
                    newString= null;
                } else {
                    newString= extras.getString("ID");
                }
            } else {
            newString= (String) savedInstanceState.getSerializable("ID");
        }
        mRestaurantId = Integer.parseInt(newString);
        LoadData();
    }


    private void LoadData() {
        mProvider.FetchRestaurant(
                mRestaurantId, new ModelCallback<Restaurant>() {
                    @Override
                    public void onSuccess(Restaurant data) {
                        ImageView img = findViewById(R.id.detailed_image_view);
                        Picasso.get().load(data.getCover_img_url()).into(img);

                        TextView name = findViewById(R.id.detailed_name);
                        name.setText(data.getName());

                        TextView description = findViewById(R.id.detailed_description);
                        description.setText(data.getDescription());

                        TextView status = findViewById(R.id.detailed_status);
                        status.setText(data.getStatus());

                        TextView deliveryFee = findViewById(R.id.detailed_delivery_fee);
                        deliveryFee.setText(data.getDelivery_fee());
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
                    }
                });
    }
}

