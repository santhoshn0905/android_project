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

public class Upcoming_RC_Adapater extends RecyclerView.Adapter<Upcoming_RC_Adapater.UpcomingViewHolder> {

    private final List<Upcoming_RC_Data> itemList;
    private final Context context;

    public Upcoming_RC_Adapater(List<Upcoming_RC_Data> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public Upcoming_RC_Adapater.UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events_rc_template, parent, false);
        return new Upcoming_RC_Adapater.UpcomingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Upcoming_RC_Adapater.UpcomingViewHolder holder, int position) {
        Upcoming_RC_Data currentItem = itemList.get(position);
        holder.Guestname.setText(currentItem.getGuestName());
        holder.MeetingTitle.setText(currentItem.getMeetingTitle());
        holder.Day.setText(String.valueOf(currentItem.getDay()));
        holder.Month.setText(currentItem.getMonth());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class UpcomingViewHolder extends RecyclerView.ViewHolder {
        TextView Guestname;
        TextView MeetingTitle;
        TextView Day;
        TextView Month;


        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);

            Guestname = itemView.findViewById(R.id.guestnameID);
            MeetingTitle = itemView.findViewById(R.id.meetingtitleID);
            Day = itemView.findViewById(R.id.dayID);
            Month = itemView.findViewById(R.id.monthID);
        }
    }

}
