package com.app.lab5_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.lab5_iot.model.UserData;

public class ConfigureActivity extends AppCompatActivity {

    private EditText inputNombre;
    private EditText inputPeso;
    private EditText inputAltura;
    private EditText inputEdad;

    private Spinner selectorIntensidad;
    private Spinner selectorObjetivo;

    private RadioGroup radioGroupGenero;
    private RadioButton radioMale;
    private RadioButton radioFemale;

    private Button buttonConfigure;


    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_configure);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputNombre = findViewById(R.id.input_nombre);
        inputPeso = findViewById(R.id.input_peso);
        inputAltura = findViewById(R.id.input_altura);
        inputEdad = findViewById(R.id.input_edad);

        selectorIntensidad = findViewById(R.id.selectorIntensidad);
        selectorObjetivo = findViewById(R.id.selectorObjetivo);

        radioGroupGenero = findViewById(R.id.radio_group_genero);
        radioMale = findViewById(R.id.radio_male);
        radioFemale = findViewById(R.id.radio_female);

        buttonConfigure = findViewById(R.id.button_configure);


        buttonConfigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = inputNombre.getText().toString();
                String peso = inputPeso.getText().toString();
                String altura = inputAltura.getText().toString();
                String edad = inputEdad.getText().toString();
                String intensidad = selectorIntensidad.getSelectedItem().toString();
                String objetivo = selectorObjetivo.getSelectedItem().toString();

                String genero = "";
                int selectedGenderId = radioGroupGenero.getCheckedRadioButtonId();
                if (selectedGenderId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedGenderId);

                    if (selectedRadioButton.getText().toString().equalsIgnoreCase("Hombre")) {
                        genero = "HOMBRE";
                    } else if (selectedRadioButton.getText().toString().equalsIgnoreCase("Mujer")) {
                        genero = "MUJER";
                    }
                } else {
                    genero = "HOMBRE";
                }

                userData = new UserData(nombre, peso, altura, edad, intensidad, objetivo, genero);

                Bundle bundle = new Bundle();
                bundle.putSerializable("userData", userData);

                Intent intent = new Intent(ConfigureActivity.this, AppActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }



}