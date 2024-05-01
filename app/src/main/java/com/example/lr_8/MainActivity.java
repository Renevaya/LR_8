package com.example.lr_8;

// Импортируем необходимые классы
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

// Определение класса MainActivity, который является наследником AppCompatActivity
public class MainActivity extends AppCompatActivity {
    // Объявление переменных
    AudioManager audioManager;
    private ImageView imageView;
    private int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3};
    private int currentImageIndex = 0;
    private MediaPlayer mediaPlayer;

    // Метод onCreate, который вызывается при создании активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация AudioManager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Получаем максимальное и текущее значение громкости и устанавливаем их на SeekBar
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volumeControl = findViewById(R.id.volumeControl);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);

        // Устанавливаем слушатель изменения громкости на SeekBar
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Инициализация ImageView и кнопок
        imageView = findViewById(R.id.imageView);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        // Устанавливаем слушатели для кнопок
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImageIndex == 0){currentImageIndex=2;}
                else{currentImageIndex = (currentImageIndex-1)%3;}
                imageView.setImageResource(images[currentImageIndex]);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex+1)%3;
                imageView.setImageResource(images[currentImageIndex]);
            }
        });

        // Инициализация MediaPlayer и запуск аудиофайла
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    // Метод для обработки нажатия на кнопку
    public void onBtnClick(View v){
        // Создаем интент для перехода на активность VideoActivity
        Intent intent = new Intent(this, VideoActivity.class);
        // Запускаем активность VideoActivity
        startActivity(intent);
    }

    // Переопределенные методы жизненного цикла активити
    @Override
    protected void onResume() {
        super.onResume();
        // При возврате на активити, если mediaPlayer не проигрывается, запускаем его
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // При уходе с активити, если mediaPlayer проигрывается, приостанавливаем его
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // При уничтожении активити освобождаем ресурсы mediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
