package com.app.lab5_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.lab5_iot.model.DataUsuario;

public class ConfigureActivity extends AppCompatActivity {

    private EditText inputNombre;
    private EditText inputPeso;
    private EditText inputAltura;
    private EditText inputEdad;

    private Spinner selectorIntensidad;
    private Spinner selectorObjetivo;

    private RadioGroup radioGroupGenero;

    private Button buttonConfigure;


    private DataUsuario dataUsuario;

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
                    genero = "UNDEFINED";
                }

                Float intensidadFactor = 0F;

                if (intensidad.equals("Poco o nada")){
                    intensidadFactor = 1.2F;
                } else if (intensidad.equals("Ejercicio ligero")) {
                    intensidadFactor = 1.375F;
                } else if (intensidad.equals("Ejercicio moderado")) {
                    intensidadFactor = 1.55F;
                } else if (intensidad.equals("Ejercicio fuerte")) {
                    intensidadFactor = 1.725F;
                } else if (intensidad.equals("Ejercicio muy fuerte")) {
                    intensidadFactor = 1.9F;
                }

                Float pesoF = Float.parseFloat(peso);
                Float alturaF = Float.parseFloat(altura);
                Float edadF = Float.parseFloat(edad);

                Float TBM = calcularTBMHarrisBenedict (pesoF, alturaF, edadF, genero, intensidadFactor);

                if (objetivo.equals("Bajar de peso")){
                    TBM = TBM - 300F;
                } else if (objetivo.equals("Subir de peso")){
                    TBM = TBM + 500F;
                } else if (objetivo.equals("Mantenerme")){
                    TBM = TBM - 0F;
                }

                dataUsuario = new DataUsuario(nombre, pesoF, alturaF, edadF, intensidad, objetivo, genero, TBM);

                Bundle bundle = new Bundle();
                bundle.putSerializable("dataUsuario", dataUsuario);

                Intent intentOK = new Intent(ConfigureActivity.this, AppActivity.class);

                intentOK.putExtras(bundle);
                intentOK.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentOK);
            }
        });

    }


    private Float calcularTBMHarrisBenedict(Float peso, Float altura, Float edad, String genero, Float intensidadFactor) {

        Float TBM = 0F;

        if (genero.equals("HOMBRE")) {
            TBM = ((10F * peso) + (6.25F * altura) - (5F * edad) + 5F);
        } else if (genero.equals("MUJER")) {
            TBM = ((10F * peso) + (6.25F * altura) - (5F * edad) - 161F);
        } else if (genero.equals("UNDEFINED")) {
            TBM = ((10F * peso) + (6.25F * altura) - (5F * edad) + 5F);
        }

        TBM = TBM * intensidadFactor;

        return TBM;
    }



}