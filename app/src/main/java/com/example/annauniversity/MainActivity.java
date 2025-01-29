package com.example.annauniversity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load a GIF into an ImageView using Glide
        ImageView dashboardIconBgImg = findViewById(R.id.dashboardtheme_1);
        Glide.with(this).asGif().load(R.drawable.dashboard_theme_gif).into(dashboardIconBgImg);

        // Initialize RecyclerView for Upcoming Events
        setupUpcomingEventsRecyclerView();

        // Initialize RecyclerView for Event Images
        setupEventImagesRecyclerView();

        // Initialize RecyclerView for Ads
        setupAdsRecyclerView();
    }
    private void setupAdsRecyclerView() {
        adsRecyclerView = findViewById(R.id.Ads_RC);
        LinearLayout adsdotIndicatorLayout = findViewById(R.id.AdsdotIndicatorLayout);

        // Create the list of event images
        List<EventImage_RC_Data> eventImages = new ArrayList<>();
        eventImages.add(new EventImage_RC_Data(R.drawable.ad1));
        eventImages.add(new EventImage_RC_Data(R.drawable.ads2));
        eventImages.add(new EventImage_RC_Data(R.drawable.ads3));

        // Set up RecyclerView adapter and layout manager
        EventImage_RC_Adapater adapter = new EventImage_RC_Adapater(eventImages, this);
        adsLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        adsRecyclerView.setLayoutManager(adsLayoutManager);
        adsRecyclerView.setAdapter(adapter);

        // Add dots for the indicator
        int itemCount = adapter.getItemCount();
        addDots(adsdotIndicatorLayout, itemCount);

        // Update dot indicator on scroll
        eventImagesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int activePosition = imageLayoutManager.findFirstVisibleItemPosition();
                updateDots(adsdotIndicatorLayout, activePosition);
            }
        });

        setupadsAutoScroll(itemCount);
    }

    private void setupUpcomingEventsRecyclerView() {
        upcomingEventsRecyclerView = findViewById(R.id.UpcomingRC);
        LinearLayout dotIndicatorLayout = findViewById(R.id.dotIndicatorLayout);

        // Create the list of events
        List<Upcoming_RC_Data> events = new ArrayList<>();
        events.add(new Upcoming_RC_Data("Alozias Aron", "Artificial Intelligence and Machine Learning", 1, "May"));
        events.add(new Upcoming_RC_Data("Akash", "CyberSecurity", 25, "Mar"));
        events.add(new Upcoming_RC_Data("Santhosh", "Research Proposal", 20, "June"));

        // Set up RecyclerView adapter and layout manager
        Upcoming_RC_Adapater adapter = new Upcoming_RC_Adapater(events, this);
        eventLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        upcomingEventsRecyclerView.setLayoutManager(eventLayoutManager);
        upcomingEventsRecyclerView.setAdapter(adapter);

        // Add dots for the indicator
        int itemCount = adapter.getItemCount();
        addDots(dotIndicatorLayout, itemCount);

        // Update dot indicator on scroll
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
        eventImagesRecyclerView = findViewById(R.id.EventImage_RC);
        LinearLayout dotIndicatorLayout = findViewById(R.id.ImagedotIndicatorLayout);

        // Create the list of event images
        List<EventImage_RC_Data> eventImages = new ArrayList<>();
        eventImages.add(new EventImage_RC_Data(R.drawable.sample1));
        eventImages.add(new EventImage_RC_Data(R.drawable.sample2));
        eventImages.add(new EventImage_RC_Data(R.drawable.sample3));
        eventImages.add(new EventImage_RC_Data(R.drawable.sample4));

        // Set up RecyclerView adapter and layout manager
        EventImage_RC_Adapater adapter = new EventImage_RC_Adapater(eventImages, this);
        imageLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        eventImagesRecyclerView.setLayoutManager(imageLayoutManager);
        eventImagesRecyclerView.setAdapter(adapter);

        // Add dots for the indicator
        int itemCount = adapter.getItemCount();
        addDots(dotIndicatorLayout, itemCount);

        // Update dot indicator on scroll
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
    // Add dots to the layout
    private void addDots(LinearLayout dotLayout, int count) {
        dotLayout.removeAllViews(); // Clear previous dots if any
        for (int i = 0; i < count; i++) {
            TextView dot = new TextView(this);
            dot.setText("\u2022"); // Unicode for dot
            dot.setTextSize(30);
            dot.setTextColor(Color.GRAY); // Default color for inactive dots
            dotLayout.addView(dot);
        }
    }

    // Update dots based on the active position
    private void updateDots(LinearLayout dotLayout, int activePosition) {
        for (int i = 0; i < dotLayout.getChildCount(); i++) {
            TextView dot = (TextView) dotLayout.getChildAt(i);
            dot.setTextColor(i == activePosition ? Color.BLACK : Color.GRAY); // Highlight active dot
        }
    }

    private void setupadsAutoScroll(int itemCount) {
        adsAutoScrollHandler = new Handler(Looper.getMainLooper());
        adsAutoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (adsCurrentPosition == itemCount) {
                    adsCurrentPosition = 0; // Reset to the first item
                }
                adsRecyclerView.smoothScrollToPosition(adsCurrentPosition);
                adsCurrentPosition++;
                adsAutoScrollHandler.postDelayed(this, 3000); // Delay of 3 seconds
            }
        };

        adsAutoScrollHandler.postDelayed(adsAutoScrollRunnable, 3000);
    }

    // Setup auto-scroll for Upcoming Events
    private void setupEventAutoScroll(int itemCount) {
        eventAutoScrollHandler = new Handler(Looper.getMainLooper());
        eventAutoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (eventCurrentPosition == itemCount) {
                    eventCurrentPosition = 0; // Reset to the first item
                }
                upcomingEventsRecyclerView.smoothScrollToPosition(eventCurrentPosition);
                eventCurrentPosition++;
                eventAutoScrollHandler.postDelayed(this, 3000); // Delay of 3 seconds
            }
        };

        eventAutoScrollHandler.postDelayed(eventAutoScrollRunnable, 3000);
    }

    // Setup auto-scroll for Event Images
    private void setupImageAutoScroll(int itemCount) {
        imageAutoScrollHandler = new Handler(Looper.getMainLooper());
        imageAutoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (imageCurrentPosition == itemCount) {
                    imageCurrentPosition = 0; // Reset to the first item
                }
                eventImagesRecyclerView.smoothScrollToPosition(imageCurrentPosition);
                imageCurrentPosition++;
                imageAutoScrollHandler.postDelayed(this, 2000); // Delay of 3 seconds
            }
        };

        imageAutoScrollHandler.postDelayed(imageAutoScrollRunnable, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventAutoScrollHandler != null) {
            eventAutoScrollHandler.removeCallbacks(eventAutoScrollRunnable);
        }
        if (imageAutoScrollHandler != null) {
            imageAutoScrollHandler.removeCallbacks(imageAutoScrollRunnable);
        }
        if (adsAutoScrollHandler != null) {
            adsAutoScrollHandler.removeCallbacks(adsAutoScrollRunnable);
        }
    }
}
