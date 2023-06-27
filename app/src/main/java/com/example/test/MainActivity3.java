package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.media.MediaRecorder;
import android.widget.Toast;
import java.io.IOException;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Environment;
import com.amplifyframework.core.Amplify;
import java.io.File;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Todo;

public class MainActivity3 extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private MediaRecorder mediaRecorder;
    private String audioFilePath;
    private String firstname;
    private String lastname;
    private String gender;
    private java.net.URL audioURL;
    private int age;
    private float bmi;
    private boolean isRecording = false;
    Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recordButton = findViewById(R.id.recordButton);
        recordButton = findViewById(R.id.recordButton);
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
        gender = getIntent().getStringExtra("gender");
        age = getIntent().getIntExtra("age", 0);
        bmi = getIntent().getFloatExtra("bmi", 0);
        Log.i("AmplifyApp", "String :" + firstname + " " + lastname + " " + gender + " " + age + " " + bmi);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord(v);
            }
        });

        // Request RECORD_AUDIO permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        }
    }

    public void startRecord(View view) {
        if (recordButton == null) {
            return;
        }
        if (!isRecording) {
            try {
                prepareMediaRecorder();
                mediaRecorder.start();
                isRecording = true;
                recordButton.setText("Stop");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            recordButton.setText("Record");
            Toast.makeText(getApplicationContext(), "Audio recorded in WAV format and saved to " + audioFilePath, Toast.LENGTH_LONG).show();
            File file = new File(audioFilePath);
            Amplify.Storage.uploadFile(
                    "rec of " + firstname + lastname,
                    file,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
            Amplify.Storage.getUrl(
                    "rec of " + firstname + lastname,
                    result -> {audioURL = result.getUrl();Log.i("MyAmplifyApp", "Successfully generated: " + audioURL);},
                    error -> Log.e("MyAmplifyApp", "URL generation failure", error)
            );
            Todo item = Todo.builder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .audioUrl(String.valueOf(audioURL))
                    .gender(gender)
                    .age(age)
                    .bmi((double) bmi)
                    .build();

            Amplify.DataStore.save(item,
                    success -> Log.i("MyAmplifyApp", "Saved item: " + success.item()),
                    error -> Log.e("MyAmplifyApp", "Could not save item to DataStore", error)
            );
            startActivity(new Intent(MainActivity3.this, MainActivity4.class));
            finish();
        }
    }

    private String prepareMediaRecorder() throws IOException {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        String audioFileName = "audio_record.wav";
        String downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        audioFilePath = downloadsDir + "/" + audioFileName;
        mediaRecorder.setOutputFile(audioFilePath);
        mediaRecorder.prepare();
        return audioFilePath;
    }

}