package com.example.annauniversity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Fragment {
    private Handler eventAutoScrollHandler;
    private Handler imageAutoScrollHandler;
    private Handler adsAutoScrollHandler;

    private RecyclerView upcomingEventsRecyclerView;
    private RecyclerView eventImagesRecyclerView;
    private RecyclerView adsRecyclerView;

    private LinearLayoutManager eventLayoutManager;
    private LinearLayoutManager imageLayoutManager;
    private LinearLayoutManager adsLayoutManager;
    private Runnable eventAutoScrollRunnable;
    private Runnable imageAutoScrollRunnable;
    private Runnable adsAutoScrollRunnable;

    private int eventCurrentPosition = 0;
    private int imageCurrentPosition = 0;
    private int adsCurrentPosition = 0;

    private View view; // Store the fragment's root view

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, container, false);
        ImageView dashboardIconBgImg = view.findViewById(R.id.dashboardtheme_1);
        Glide.with(this).asGif().load(R.drawable.dashboard_theme_gif).into(dashboardIconBgImg);
        setupUpcomingEventsRecyclerView();
        setupEventImagesRecyclerView();
        setupAdsRecyclerView();

        return view;
    }

    private void setupAdsRecyclerView() {
        adsRecyclerView = view.findViewById(R.id.Ads_RC);
        LinearLayout adsdotIndicatorLayout = view.findViewById(R.id.AdsdotIndicatorLayout);

        List<EventImage_RC_Data> eventImages = new ArrayList<>();
        eventImages.add(new EventImage_RC_Data(R.drawable.ad1));
        eventImages.add(new EventImage_RC_Data(R.drawable.ads2));
        eventImages.add(new EventImage_RC_Data(R.drawable.ads3));

        EventImage_RC_Adapater adapter = new EventImage_RC_Adapater(eventImages, getContext());
        adsLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        adsRecyclerView.setLayoutManager(adsLayoutManager);
        adsRecyclerView.setAdapter(adapter);

        int itemCount = adapter.getItemCount();
        addDots(adsdotIndicatorLayout, itemCount);

        adsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int activePosition = adsLayoutManager.findFirstVisibleItemPosition();
                updateDots(adsdotIndicatorLayout, activePosition);
            }
        });

        setupAdsAutoScroll(itemCount);
    }

    private void setupUpcomingEventsRecyclerView() {
        upcomingEventsRecyclerView = view.findViewById(R.id.UpcomingRC);
        LinearLayout dotIndicatorLayout = view.findViewById(R.id.dotIndicatorLayout);

        List<Upcoming_RC_Data> events = new ArrayList<>();
        events.add(new Upcoming_RC_Data("Alozias Aron", "AI and Machine Learning", 1, "May"));
        events.add(new Upcoming_RC_Data("Akash", "CyberSecurity", 25, "Mar"));
        events.add(new Upcoming_RC_Data("Santhosh", "Research Proposal", 20, "June"));

        Upcoming_RC_Adapater adapter = new Upcoming_RC_Adapater(events, getContext());
        eventLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        upcomingEventsRecyclerView.setLayoutManager(eventLayoutManager);
        upcomingEventsRecyclerView.setAdapter(adapter);

        int itemCount = adapter.getItemCount();
        addDots(dotIndicatorLayout, itemCount);

        upcomingEventsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int activePosition = eventLayoutManager.findFirstVisibleItemPosition();
                updateDots(dotIndicatorLayout, activePosition);
            }
        });

        setupEventAutoScroll(itemCount);
    }

    private void setupEventImagesRecyclerView() {
        eventImagesRecyclerView = view.findViewById(R.id.EventImage_RC);
        LinearLayout dotIndicatorLayout = view.findViewById(R.id.ImagedotIndicatorLayout);

        List<EventImage_RC_Data> eventImages = new ArrayList<>();
        eventImages.add(new EventImage_RC_Data(R.drawable.sample1));
        eventImages.add(new EventImage_RC_Data(R.drawable.sample2));
        eventImages.add(new EventImage_RC_Data(R.drawable.sample3));
        eventImages.add(new EventImage_RC_Data(R.drawable.sample4));

        EventImage_RC_Adapater adapter = new EventImage_RC_Adapater(eventImages, getContext());
        imageLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        eventImagesRecyclerView.setLayoutManager(imageLayoutManager);
        eventImagesRecyclerView.setAdapter(adapter);

        int itemCount = adapter.getItemCount();
        addDots(dotIndicatorLayout, itemCount);

        eventImagesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int activePosition = imageLayoutManager.findFirstVisibleItemPosition();
                updateDots(dotIndicatorLayout, activePosition);
            }
        });

        setupImageAutoScroll(itemCount);
    }
    private void setupImageAutoScroll(int itemCount) {
        imageAutoScrollHandler = new Handler(Looper.getMainLooper());
        imageAutoScrollRunnable = () -> {
            if (imageCurrentPosition == itemCount) {
                imageCurrentPosition = 0;
            }
            eventImagesRecyclerView.smoothScrollToPosition(imageCurrentPosition);
            imageCurrentPosition++;
            imageAutoScrollHandler.postDelayed(imageAutoScrollRunnable, 3000);
        };
        imageAutoScrollHandler.postDelayed(imageAutoScrollRunnable, 3000);
    }

    private void setupEventAutoScroll(int itemCount) {
        eventAutoScrollHandler = new Handler(Looper.getMainLooper());
        eventAutoScrollRunnable = () -> {
            if (eventCurrentPosition == itemCount) {
                eventCurrentPosition = 0;
            }
            upcomingEventsRecyclerView.smoothScrollToPosition(eventCurrentPosition);
            eventCurrentPosition++;
            eventAutoScrollHandler.postDelayed(eventAutoScrollRunnable, 3000);
        };
        eventAutoScrollHandler.postDelayed(eventAutoScrollRunnable, 3000);
    }

    private void addDots(LinearLayout dotLayout, int count) {
        dotLayout.removeAllViews();
        for (int i = 0; i < count; i++) {
            TextView dot = new TextView(getContext());
            dot.setText("\u2022");
            dot.setTextSize(30);
            dot.setTextColor(Color.GRAY);
            dotLayout.addView(dot);
        }
    }

    private void updateDots(LinearLayout dotLayout, int activePosition) {
        for (int i = 0; i < dotLayout.getChildCount(); i++) {
            TextView dot = (TextView) dotLayout.getChildAt(i);
            dot.setTextColor(i == activePosition ? Color.BLACK : Color.GRAY);
        }
    }

    private void setupAdsAutoScroll(int itemCount) {
        adsAutoScrollHandler = new Handler(Looper.getMainLooper());
        adsAutoScrollRunnable = () -> {
            if (adsCurrentPosition == itemCount) {
                adsCurrentPosition = 0;
            }
            adsRecyclerView.smoothScrollToPosition(adsCurrentPosition);
            adsCurrentPosition++;
            adsAutoScrollHandler.postDelayed(adsAutoScrollRunnable, 3000);
        };
        adsAutoScrollHandler.postDelayed(adsAutoScrollRunnable, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (eventAutoScrollHandler != null) eventAutoScrollHandler.removeCallbacks(eventAutoScrollRunnable);
        if (imageAutoScrollHandler != null) imageAutoScrollHandler.removeCallbacks(imageAutoScrollRunnable);
        if (adsAutoScrollHandler != null) adsAutoScrollHandler.removeCallbacks(adsAutoScrollRunnable);
    }
}
