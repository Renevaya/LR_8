package com.example.lr_8;

// Импортируем необходимые классы
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

// Определение класса VideoActivity, который является наследником AppCompatActivity
public class VideoActivity extends AppCompatActivity {

    // Метод onCreate, который вызывается при создании активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Инициализация VideoView
        VideoView videoView = findViewById(R.id.videoView);

        // Получаем путь к видео из ресурсов приложения и создаем Uri
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);

        // Устанавливаем URI видео для воспроизведения
        videoView.setVideoURI(uri);

        // Создаем объект MediaController для управления воспроизведением видео
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        // Устанавливаем якорь MediaController на VideoView
        mediaController.setAnchorView(videoView);
    }

    // Метод для обработки нажатия на кнопку
    public void onBtnClick(View v) {
        // Создаем интент для перехода на активность MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        // Запускаем активность MainActivity
        startActivity(intent);
    }
}
