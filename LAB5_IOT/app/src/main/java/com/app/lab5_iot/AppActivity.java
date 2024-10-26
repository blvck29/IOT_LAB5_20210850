package com.app.lab5_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.lab5_iot.model.CircularTMB;
import com.app.lab5_iot.model.DataCalorias;
import com.app.lab5_iot.model.DataUsuario;

public class AppActivity extends AppCompatActivity {

    private CircularTMB circularTMBProgressView;
    private DataUsuario dataUsuario;
    private DataCalorias dataCalorias;

    private Button inputCalorias;
    private Button inputEjercicio;

    private ImageButton configurarNotificaciones;

    private Button addFood1;
    private Button addFood2;
    private Button addFood3;
    private Button addFood4;
    private Button addFood5;


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

        inputCalorias = findViewById(R.id.input_calorias);
        inputEjercicio = findViewById(R.id.input_ejercicio);
        configurarNotificaciones = findViewById(R.id.configurar_notificaciones);

        addFood1 = findViewById(R.id.add_food1);
        addFood2 = findViewById(R.id.add_food2);
        addFood3 = findViewById(R.id.add_food3);
        addFood4 = findViewById(R.id.add_food4);
        addFood5 = findViewById(R.id.add_food5);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            dataUsuario = (DataUsuario) bundle.getSerializable("dataUsuario");
            dataCalorias = new DataCalorias();
            dataCalorias.setCaloriasConsumidas(0F);
            dataCalorias.setCaloriasTotales(dataUsuario.getTMB());
        }

        configureCircularTMBProgressView(dataCalorias);


        // INICIA LA LOGICA


        addFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 942F);
                configureCircularTMBProgressView(dataCalorias);
            }
        });

        addFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 1242F);
                configureCircularTMBProgressView(dataCalorias);
            }
        });

        addFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 1062F);
                configureCircularTMBProgressView(dataCalorias);
            }
        });

        addFood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 1542F);
                configureCircularTMBProgressView(dataCalorias);
            }
        });

        addFood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 966F);
                configureCircularTMBProgressView(dataCalorias);
            }
        });

    }

    private void configureCircularTMBProgressView(DataCalorias dataCalorias) {

        Float actualData = dataCalorias.getCaloriasConsumidas();
        Float totalData = dataCalorias.getCaloriasTotales();

        Float actual = actualData;
        Float total = totalData;
        int totalSinDecimales = total.intValue();
        float porcentaje = (actual * 100f) / totalSinDecimales;
        String textCenter = actual + " / " + totalSinDecimales + " kcal";

        circularTMBProgressView.setPorcentaje(porcentaje, textCenter);
    }
}
