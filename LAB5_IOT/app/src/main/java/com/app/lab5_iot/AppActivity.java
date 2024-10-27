package com.app.lab5_iot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

    private LinearLayout alertaSuperaCalorias;


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

        alertaSuperaCalorias = findViewById(R.id.alerta_supera_calorias);

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


        inputCalorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = getLayoutInflater().inflate(R.layout.add_comida_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(AppActivity.this);
                builder.setView(dialogView)
                        .setTitle("Ingresar Comida")
                        .setPositiveButton("Aceptar", (dialog, which) -> {
                            EditText textFoodName = dialogView.findViewById(R.id.food_name);
                            EditText textCalories = dialogView.findViewById(R.id.add_calories);


                            String calories = textCalories.getText().toString();
                            String foodName = textFoodName.getText().toString();

                            dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + Float.parseFloat(calories));
                            configureCircularTMBProgressView(dataCalorias);

                            if (superaCalorias()) {
                                alertaSuperaCalorias.setVisibility(View.VISIBLE);
                            } else {
                                alertaSuperaCalorias.setVisibility(View.GONE);
                            }

                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

                builder.create().show();
            }
        });

        inputEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View dialogView = getLayoutInflater().inflate(R.layout.add_ejercicio_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(AppActivity.this);
                builder.setView(dialogView)
                        .setTitle("Ingresar Ejercicio")
                        .setPositiveButton("Aceptar", (dialog, which) -> {

                            EditText textExerciseName = dialogView.findViewById(R.id.exercise_name);
                            EditText textCalories = dialogView.findViewById(R.id.minus_calories);

                            String calories = textCalories.getText().toString();
                            String exerciseName = textExerciseName.getText().toString();

                            if (dataCalorias.getCaloriasConsumidas() > 0) {

                                float caloriesValue = Float.parseFloat(calories);
                                Float newValue = dataCalorias.getCaloriasConsumidas() - caloriesValue;

                                if (newValue >= 0){
                                    dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() - caloriesValue);
                                    configureCircularTMBProgressView(dataCalorias);
                                } else {
                                    dataCalorias.setCaloriasConsumidas(0F);
                                    configureCircularTMBProgressView(dataCalorias);
                                }

                                if (superaCalorias()) {
                                    alertaSuperaCalorias.setVisibility(View.VISIBLE);
                                } else {
                                    alertaSuperaCalorias.setVisibility(View.GONE);
                                }

                            } else {
                                AlertDialog.Builder builderNoCalorias = new AlertDialog.Builder(AppActivity.this);
                                builderNoCalorias.setMessage("No puedes ingresar un ejercicio sin calorías consumidas.")
                                        .setNegativeButton("OK", (dialogNoCalorias, whichNoCalorias) -> dialogNoCalorias.dismiss())
                                        .show();
                            }
                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

                builder.create().show();
            }
        });




        addFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 942F);
                configureCircularTMBProgressView(dataCalorias);

                if (superaCalorias()) {
                    alertaSuperaCalorias.setVisibility(View.VISIBLE);
                } else {
                    alertaSuperaCalorias.setVisibility(View.GONE);
                }
            }
        });

        addFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 1242F);
                configureCircularTMBProgressView(dataCalorias);

                if (superaCalorias()) {
                    alertaSuperaCalorias.setVisibility(View.VISIBLE);
                } else {
                    alertaSuperaCalorias.setVisibility(View.GONE);
                }
            }
        });

        addFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 1062F);
                configureCircularTMBProgressView(dataCalorias);

                if (superaCalorias()) {
                    alertaSuperaCalorias.setVisibility(View.VISIBLE);
                } else {
                    alertaSuperaCalorias.setVisibility(View.GONE);
                }
            }
        });

        addFood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 1542F);
                configureCircularTMBProgressView(dataCalorias);

                if (superaCalorias()) {
                    alertaSuperaCalorias.setVisibility(View.VISIBLE);
                } else {
                    alertaSuperaCalorias.setVisibility(View.GONE);
                }
            }
        });

        addFood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCalorias.setCaloriasConsumidas(dataCalorias.getCaloriasConsumidas() + 966F);
                configureCircularTMBProgressView(dataCalorias);

                if (superaCalorias()) {
                    alertaSuperaCalorias.setVisibility(View.VISIBLE);
                } else {
                    alertaSuperaCalorias.setVisibility(View.GONE);
                }
            }
        });

    }

    private boolean superaCalorias(){

        if (dataCalorias.getCaloriasConsumidas() > dataCalorias.getCaloriasTotales()){
            return true;
        } else {
            return false;
        }
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
