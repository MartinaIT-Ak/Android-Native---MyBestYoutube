package com.ita.mybestyoutube;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ita.mybestyoutube.database.VideoDatabase;
import com.ita.mybestyoutube.pojos.Video;

public class AddVideoActivity extends AppCompatActivity {
    Context context;
    Toolbar toolbar;
    EditText videoTitre;
    EditText videoDesc;
    EditText videoUrl;
    Spinner spinnerCategory;
    Button btnAdd;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // récupère le context
        context = getApplicationContext();
        VideoDatabase db = VideoDatabase.getDb(context);

        // crée l'action bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // affiche le back
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //recup des elements
        videoTitre = findViewById(R.id.videoTitre);
        videoDesc = findViewById(R.id.videoDesc);
        videoUrl = findViewById(R.id.videoUrl);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        // crée le spinner
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.spinner_category_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerCategory.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newVideoTitre = videoTitre.getText().toString();
                String newVideoDesc = videoDesc.getText().toString();
                String newVideoUrl = videoUrl.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();
                //verifier si Titre, Desc et Url renseignes
                if(!newVideoTitre.isEmpty() && !newVideoDesc.isEmpty() && !newVideoUrl.isEmpty()) {
                    //ajouter le video au bdd
                    Video video = new Video(newVideoTitre,
                            newVideoDesc,
                            newVideoUrl,
                            category,
                            false);
                    VideoDatabase.getDb(context).videoDao().add(video);
                    finish();
                } else {
                    //affiche toast notif erreur saisie
                    AfficherToast(newVideoTitre.isEmpty() ? getString(R.string.missingTitre) :
                            newVideoDesc.isEmpty() ? getString(R.string.missingDesc) : getString(R.string.missingUrl));
                }
            }
        });
    }

    public void AfficherToast(String text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}