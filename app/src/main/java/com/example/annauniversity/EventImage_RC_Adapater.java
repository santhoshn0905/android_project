package com.example.annauniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventImage_RC_Adapater extends RecyclerView.Adapter<EventImage_RC_Adapater.UpcomingViewHolder> {

    private final List<EventImage_RC_Data> itemList;
    private final Context context;

    public EventImage_RC_Adapater(List<EventImage_RC_Data> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventImage_RC_Adapater.UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventimage_rc_template, parent, false);
        return new EventImage_RC_Adapater.UpcomingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EventImage_RC_Adapater.UpcomingViewHolder holder, int position) {
        EventImage_RC_Data currentItem = itemList.get(position);
        holder.image.setImageResource(currentItem.getImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class UpcomingViewHolder extends RecyclerView.ViewHolder {
        ImageView image;



        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.EventImageID);

        }
    }

}
