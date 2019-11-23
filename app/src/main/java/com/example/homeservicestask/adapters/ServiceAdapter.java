package com.example.homeservicestask.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.homeservicestask.R;
import com.example.homeservicestask.models.Services;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.Holder> {

    ArrayList<Services> data = new ArrayList<>();
    Context context;
    int type_2 = 0;
    int type_4 = 1;

    public ServiceAdapter(Context context, ArrayList<Services> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ServiceAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == type_2) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.service_item_2, parent, false);
            return new ServiceAdapter.Holder(v);

        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.service_item, parent, false);
            return new ServiceAdapter.Holder(v);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.Holder holder, int position) {

        holder.name.setText(data.get(position).getName());
        holder.txtDescription.setText(data.get(position).getDescription());
       // if(!data.get(position).getImage().equalsIgnoreCase("1"))
       Glide.with(context).load(data.get(position).getImage()).into(holder.serviceImg);
holder.serviceImg.setColorFilter(context.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//        Picasso.get()
//                .load(data.get(position).getImage())
//                .resize(50, 50)
//                .centerCrop()
//                .into(holder.serviceImg);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public int getItemViewType(int position) {
        if (data.get(position).getType() == 2)
            return type_2;
        else
            return type_4;

    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.description)
        TextView txtDescription;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.service_img)
        ImageView serviceImg;

        public Holder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }


}
