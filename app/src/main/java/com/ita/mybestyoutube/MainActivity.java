package com.ita.mybestyoutube;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ita.mybestyoutube.adapter.VideoAdapter;
import com.ita.mybestyoutube.database.VideoDatabase;
import com.ita.mybestyoutube.pojos.Video;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private static final String TAG = "MyBestYoutube";
    private RecyclerView rvVideo;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //recup des elements
        toolbar = findViewById(R.id.toolbar);
        rvVideo = findViewById(R.id.rvVideo);

        //affecter le toolbar comme ActionBar
        setSupportActionBar(toolbar);

        // crée le layoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvVideo.setHasFixedSize(true);
        // lie le layoutManager au RecyclerView
        rvVideo.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //creer le menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //test l'id de l'item selectionne
        if(item.getItemId() == R.id.addVideo) {
            //creer un Intent
            Intent intent = new Intent(getApplicationContext(), AddVideoActivity.class);
            //demarer l'activity
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

        new Thread() {
            public void run() {
                runOnUiThread(() -> {
                    // récupère la liste des todos
                    List<Video> todos = VideoDatabase.getDb(getApplicationContext()).videoDao().getAll();

                    // crée l'adapter et lie l'adapter avec le RecyclerView
                    VideoAdapter videoAdapter = new VideoAdapter(todos);
                    rvVideo.setAdapter(videoAdapter);
                });
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstance() called");
    }
}