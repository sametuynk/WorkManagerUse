package com.example.workmanagerkullanimi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.util.Log;

import com.example.workmanagerkullanimi.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonYap.setOnClickListener(v -> {
            /*Constraints calismaKosulu=new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();


            WorkRequest istek=new OneTimeWorkRequest.Builder(MyWorker.class)
                    .setInitialDelay(10, TimeUnit.SECONDS)
                    .setConstraints(calismaKosulu)
                    .build();

            WorkManager.getInstance(this).enqueue(istek);*/

            WorkRequest istek=new PeriodicWorkRequest.Builder(MyWorkerBildirimi.class,15,TimeUnit.MINUTES)
                    .setInitialDelay(10, TimeUnit.SECONDS)
                    .build();

            WorkManager.getInstance(this).enqueue(istek);

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(istek.getId())
                    .observe(this,workInfo -> {
                        String durum=workInfo.getState().name();
                        Log.e("Arkaplan İşlem Durumu",durum);
                    });
        });
    }
}