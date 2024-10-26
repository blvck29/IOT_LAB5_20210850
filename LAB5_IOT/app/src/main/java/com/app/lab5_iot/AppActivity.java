package com.app.lab5_iot;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.lab5_iot.model.CircularTMB;
import com.app.lab5_iot.model.DataUsuario;

public class AppActivity extends AppCompatActivity {


    private CircularTMB circularTMBProgressView;
    private DataUsuario dataUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        circularTMBProgressView = findViewById(R.id.circularTMBProgressView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null) {
            dataUsuario = (DataUsuario) bundle.getSerializable("dataUsuario");
        }

        configureCircularTMBProgressView(0, dataUsuario.getTMB());


    }


    private void configureCircularTMBProgressView(int actualData, Float totalData){
        int actual = actualData;
        Float total = totalData;
        int totalSinDecimales = total.intValue();
        float porcentaje = (actual * 100f) / totalSinDecimales;
        String textCenter = actual + " / " + totalSinDecimales + " kcal";

        circularTMBProgressView.setPorcentaje(porcentaje, textCenter);
    }




}