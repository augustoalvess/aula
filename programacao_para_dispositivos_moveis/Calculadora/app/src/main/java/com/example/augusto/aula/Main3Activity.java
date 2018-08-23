package com.example.augusto.aula;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Main3Activity extends AppCompatActivity {

    private Button backBtn;
    private Button saveBtn;
    private VideoView tuttoVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        backBtn = (Button) findViewById(R.id.back_btn);
        saveBtn = (Button) findViewById(R.id.save_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Main3Activity.this).setTitle("Aviso").setMessage("Todas as suas alterações serão perdidas, deseja prosseguir?")
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                            }
                        }).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main3Activity.this,"Configurações salvas com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        tuttoVideo = (VideoView) findViewById(R.id.tutto_video);
        tuttoVideo.setVideoURI(Uri.parse("http://www.youtube.com/watch?v=HR5uq-WDaJ8"));
        MediaController mc = new MediaController(Main3Activity.this);
        tuttoVideo.setMediaController(mc);
        tuttoVideo.requestFocus();
        tuttoVideo.start();
        mc.show();
    }
}
