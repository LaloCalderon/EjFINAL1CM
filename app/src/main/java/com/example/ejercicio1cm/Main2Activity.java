package com.example.ejercicio1cm;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;
import com.example.ejercicio1cm.Modelo.Usuario;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = new Bundle();
        bundle=getIntent().getExtras();
        Usuario usuario=(Usuario) bundle.getSerializable("usuario");

        //String year=bundle.getString("anos");
        //int month=bundle.getInt("meses");
        //int day=bundle.getInt("dias");

        final Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        char l1=usuario.getApat().toUpperCase().charAt(0);
        char l2=usuario.getApat().toUpperCase().charAt(1);
        char l3=usuario.getAmat().toUpperCase().charAt(0);
        char l4=usuario.getName().toUpperCase().charAt(0);
        char l5=year.charAt(2);
        char l6=year.charAt(3);

        if(month<10){
            if(day<10){
                Toast.makeText(Main2Activity.this, "RFC:"+l1+l2+l3+l4+l5+l6+"0"+month+"0"+day, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Main2Activity.this, "RFC:"+l1+l2+l3+l4+l5+l6+"0"+month+day, Toast.LENGTH_SHORT).show();}
        }else{
            if(day<10){
                Toast.makeText(Main2Activity.this, "RFC:"+l1+l2+l3+l4+l5+l6+month+"0"+day, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Main2Activity.this, "RFC:"+l1+l2+l3+l4+l5+l6+month+day, Toast.LENGTH_SHORT).show();
            }
        }
    }
}


