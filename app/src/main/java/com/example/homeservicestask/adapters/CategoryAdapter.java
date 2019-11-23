package com.example.homeservicestask.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homeservicestask.R;
import com.example.homeservicestask.models.CategoryData;
import com.example.homeservicestask.ui.MapsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    ArrayList<CategoryData> data =new ArrayList<>();

   MapsActivity mapActivity;
    int selectedIndex = -1;
    // public Hfragment hfragment;
    boolean isSelected = false;

    public CategoryAdapter(MapsActivity activity) {

this.mapActivity=activity;

    }


    @NonNull
    @Override
    public CategoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_service_item, parent, false);
        return new CategoryAdapter.Holder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Holder holder, int position) {

        Glide.with(mapActivity).load(data.get(position).getImage()).into(holder.mainImg);
//holder.button.setColorFilter(Color.argb(100, 77, 73, 74));
        holder.mainImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mapActivity.setCategoryId(position);
               mapActivity.addServices(position);




            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<CategoryData> data) {
        this.data = data;
   notifyDataSetChanged();
    }

    public CategoryData getCatAt(int position) {
        return data.get(position);
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_img)
        ImageView mainImg;


        public Holder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }


}
