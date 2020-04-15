package com.example.ejercicio1cm;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ejercicio1cm.Modelo.Usuario;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity implements OnClickListener {
    EditText etname, etapat, etamat;
    EditText etPlannedDate;
    Button boton;
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPlannedDate = findViewById(R.id.etPlannedDate);
        etapat = findViewById(R.id.etapat);
        etname = findViewById(R.id.etname);
        etamat=findViewById(R.id.etamat);
        boton = findViewById(R.id.boton);
        boton.setOnClickListener(this);
        etPlannedDate.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.boton:
                if (verificar()){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usuario", usuario);

                    //AQUÍ DEBE DE MANDARSE A TRAER LOS VALORES DEL PEACKER PARA MANDARLOS A LA SEGUNDA ACTIVITY PERO NO SÉ COMO!! ):
                    //onDateSet(DatePicker datePicker, int year, int month, int day);

                    //bundle.putInt("anos", day);
                    //bundle.putInt("dias", month);
                    //bundle.putInt("meses", year);

                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.eetver), Toast.LENGTH_SHORT).show();
                }  break;
            case R.id.etPlannedDate: //boton fecha
                        showDatePickerDialog();
                break;
            default:
                break;
                    }
       }

    private boolean verificar() {
        if (etname.getText().length() == 0) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.eetnv), Toast.LENGTH_SHORT).show();
            return false;
        }else {
             if(etapat.getText().length() == 0){
                 Toast.makeText(MainActivity.this, getResources().getString(R.string.eetapv), Toast.LENGTH_SHORT).show();
                 return false;
             }
             else{
                if (etamat.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.eetamv), Toast.LENGTH_SHORT).show();
                    return false;
                }else{
                    if(etname.getText().length() <2){
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.eetnin), Toast.LENGTH_SHORT).show();
                        return false;
                    }else{
                        if(etapat.getText().length() <2) {
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.eetapin), Toast.LENGTH_SHORT).show();
                            return false;
                        }else{
                            if(etamat.getText().length() <2) {
                                Toast.makeText(MainActivity.this, getResources().getString(R.string.eetamin), Toast.LENGTH_SHORT).show();
                                return false;
                            }else{
                                if(isNumeric(etname.getText().toString())) return false;
                                if(isNumeric(etamat.getText().toString())) return false;
                                if(isNumeric(etapat.getText().toString())) return false;
                                else {
                                    usuario = new Usuario(etname.getText().toString(), etapat.getText().toString(), etamat.getText().toString());
                                    //Toast.makeText(MainActivity.this, getResources().getString(R.string.tuv), Toast.LENGTH_SHORT).show();

                                    //public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                        }

                                    }
                                    return true;
                                    }
                                }
                            }
                        }
                    }
                }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etPlannedDate.setText(selectedDate);
                verificazodiaco(month,day);
                verificachino(year);
                verficaedad(day,month,year);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public  boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            Toast.makeText(MainActivity.this, getResources().getString(R.string.tcn), Toast.LENGTH_SHORT).show();
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

    private void verficaedad(int day, int month, int year) {
        final Calendar c = Calendar.getInstance();
        int anoactual = c.get(Calendar.YEAR);
        int mesactual = c.get(Calendar.MONTH);
        int diaactual = c.get(Calendar.DAY_OF_MONTH);

        if(year<=anoactual) {  //>=2020 si es 2020 month<mesactual
            if (month <= mesactual ) { //Ya cumplio anos
                if(day<=diaactual){ //Ya cumpllio
                    int edad = anoactual - year;
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tedad)+edad+getResources().getString(R.string.tanos), Toast.LENGTH_SHORT).show();
                }else{
                    int edad = (anoactual - year)-1;
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tedad)+edad+getResources().getString(R.string.tanos), Toast.LENGTH_SHORT).show();
                }
            }else{ //no ah cumplido anos
                int edad = (anoactual - year)-1;
                Toast.makeText(MainActivity.this, getResources().getString(R.string.tedad)+edad+getResources().getString(R.string.tanos), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.tnovivido), Toast.LENGTH_SHORT).show();
        }
    }

    private void verificachino(int year){
        if(year==1900 || year==1912 || year==1924 || year==1936 || year==1948 || year==1960|| year==1972|| year==1984 || year==1996|| year==2008|| year==2020 ){
            Toast.makeText(MainActivity.this, getResources().getString(R.string.eethcr), Toast.LENGTH_SHORT).show();
        }else{
            if(year==1901 || year==1913 || year==1925 || year==1937 || year==1949 || year==1961|| year==1973|| year==1985 || year==1997|| year==2009|| year==2021){
                Toast.makeText(MainActivity.this, getResources().getString(R.string.thcb), Toast.LENGTH_SHORT).show();
            }else{
                if(year==1902 || year==1914 || year==1926 || year==1938 || year==1950 || year==1962|| year==1974|| year==1986 || year==1998|| year==2010|| year==2022){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.thct), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this, "Horóscopo chino:Tigre", Toast.LENGTH_SHORT).show();
                }else{
                    if(year==1903 || year==1915 || year==1927 || year==1939 || year==1951 || year==1963|| year==1975|| year==1987 || year==1999|| year==2011|| year==2023){
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.thcc), Toast.LENGTH_SHORT).show();
                    }else{
                        if(year==1904 || year==1916 || year==1928 || year==1940 || year==1952 || year==1964|| year==1976|| year==1988 || year==2000|| year==2012|| year==2024){
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.thcd), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this, "Horóscopo chino:Dragón", Toast.LENGTH_SHORT).show();
                        }else{
                            if(year==1905 || year==1917 || year==1929 || year==1941 || year==1953 || year==1965|| year==1977|| year==1989 || year==2001|| year==2013|| year==2025){
                                Toast.makeText(MainActivity.this, getResources().getString(R.string.thcs), Toast.LENGTH_SHORT).show();
                            }else{
                                if(year==1906 || year==1918 || year==1930 || year==1942 || year==1954 || year==1966|| year==1978|| year==1990 || year==2002|| year==2014|| year==2026){
                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.thcca), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(MainActivity.this, "Horóscopo chino:Caballo", Toast.LENGTH_SHORT).show();
                                }else{
                                    if(year==1907 || year==1919 || year==1931 || year==1943 || year==1955 || year==1967|| year==1979|| year==1991 || year==2003|| year==2015|| year==2027){
                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.thccabra), Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(MainActivity.this, "Horóscopo chino:Cabra", Toast.LENGTH_SHORT).show();
                                    }else{
                                        if(year==1908 || year==1920 || year==1932 || year==1944 || year==1956 || year==1968|| year==1980|| year==1992 || year==2004|| year==2016|| year==2028){
                                            Toast.makeText(MainActivity.this, getResources().getString(R.string.thcm), Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(MainActivity.this, "Horóscopo chino:Mono", Toast.LENGTH_SHORT).show();
                                        }else{
                                            if(year==1909 || year==1921 || year==1933 || year==1945 || year==1957 || year==1969|| year==1981|| year==1993 || year==2005|| year==2017|| year==2029){
                                                Toast.makeText(MainActivity.this, getResources().getString(R.string.thcg), Toast.LENGTH_SHORT).show();
                                                //Toast.makeText(MainActivity.this, "Horsócopo chino:Gallo", Toast.LENGTH_SHORT).show();
                                            }else{
                                                if(year==1910 || year==1922 || year==1934 || year==1946 || year==1958 || year==1970|| year==1982|| year==1994 || year==2006|| year==2018|| year==2030){
                                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.thcp), Toast.LENGTH_SHORT).show();
                                                    //Toast.makeText(MainActivity.this, "Horóscopo chino:Perro", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    if(year==1911 || year==1923 || year==1935 || year==1947 || year==1959 || year==1971|| year==1983|| year==1995 || year==2019|| year==2016|| year==2031){
                                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.thcchancho), Toast.LENGTH_SHORT).show();
                                                        //Toast.makeText(MainActivity.this, "Horóscopo chino:Chancho", Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.invyear), Toast.LENGTH_SHORT).show();
                                                        //Toast.makeText(MainActivity.this, "Año desconocido", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void verificazodiaco(int month, int day) {
        if((month+1)==1 & day<=20){
            Toast.makeText(MainActivity.this, getResources().getString(R.string.tszcap), Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, "Signo zodiacal:Capricornio", Toast.LENGTH_SHORT).show();
        }else{
            if((month+1)==1 & day>20){
                Toast.makeText(MainActivity.this, getResources().getString(R.string.tszac), Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, "Signo zodiacal:Acuario", Toast.LENGTH_SHORT).show();
            } else{
                if((month+1)==2 & day<20){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tszac), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this, "Signo zodiacal:Acuario", Toast.LENGTH_SHORT).show();
                }else{
                    if((month+1)==2 & day>=20){
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.tszp), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MainActivity.this, "Signo zodiacal:Piscis", Toast.LENGTH_SHORT).show();
                    }else{
                        if((month+1)==3 & day<=20){
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.tszp), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this, "Signo zodiacal:Piscis", Toast.LENGTH_SHORT).show();
                        }else{
                            if((month+1)==3 & day>20){
                                Toast.makeText(MainActivity.this, getResources().getString(R.string.tszar), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(MainActivity.this, "Signo zodiacal:Aries", Toast.LENGTH_SHORT).show();
                            }else{
                                if((month+1)==4 & day<=20){
                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tszar), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(MainActivity.this, "Signo zodiacal:Aries", Toast.LENGTH_SHORT).show();
                                }else{
                                    if((month+1)==4 & day>20){
                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.tszt), Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(MainActivity.this, "Signo zodiacal:Tauro", Toast.LENGTH_SHORT).show();
                                    }else{
                                        if((month+1)==5 & day<=20){
                                            Toast.makeText(MainActivity.this, getResources().getString(R.string.tszt), Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(MainActivity.this, "Signo zodiacal:Tauro", Toast.LENGTH_SHORT).show();
                                        }else{
                                            if((month+1)==5 & day>20){
                                                Toast.makeText(MainActivity.this, getResources().getString(R.string.tszg), Toast.LENGTH_SHORT).show();
                                                //Toast.makeText(MainActivity.this, "Signo zodiacal:Geminis", Toast.LENGTH_SHORT).show();
                                            }else{
                                                if((month+1)==6 & day<=21){
                                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tszg), Toast.LENGTH_SHORT).show();
                                                    //Toast.makeText(MainActivity.this, "Signo zodiacal:Géminis", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    if((month+1)==6 & day>21){
                                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.tszcan), Toast.LENGTH_SHORT).show();
                                                        //Toast.makeText(MainActivity.this, "Signo zodiacal:Cáncer", Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        if((month+1)==7 & day<23){
                                                            Toast.makeText(MainActivity.this, getResources().getString(R.string.tszcan), Toast.LENGTH_SHORT).show();
                                                            //Toast.makeText(MainActivity.this, "Signo zodiacal:Cáncer", Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            if((month+1)==7 & day>=23){
                                                                Toast.makeText(MainActivity.this, getResources().getString(R.string.tszl), Toast.LENGTH_SHORT).show();
                                                                //Toast.makeText(MainActivity.this, "Signo zodiacal:Leo", Toast.LENGTH_SHORT).show();
                                                            }else{
                                                                if((month+1)==8 & day<=23){
                                                                    //Toast.makeText(MainActivity.this, "Signo zodiacal:Leo", Toast.LENGTH_SHORT).show();
                                                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tszl), Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    if((month+1)==8 & day>23){
                                                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.tszv), Toast.LENGTH_SHORT).show();
                                                                        //Toast.makeText(MainActivity.this, "Signo zodiacal:Virgo", Toast.LENGTH_SHORT).show();
                                                                    }else{
                                                                        if((month+1)==9 & day<=22){
                                                                            Toast.makeText(MainActivity.this, getResources().getString(R.string.tszv), Toast.LENGTH_SHORT).show();
                                                                            //Toast.makeText(MainActivity.this, "Signo zodiacal:Virgo", Toast.LENGTH_SHORT).show();
                                                                        }else{
                                                                            if((month+1)==9 & day>22){
                                                                                Toast.makeText(MainActivity.this, getResources().getString(R.string.tszli), Toast.LENGTH_SHORT).show();
                                                                            }else{
                                                                                if((month+1)==10 & day<=22){
                                                                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tszli), Toast.LENGTH_SHORT).show();
                                                                                    //Toast.makeText(MainActivity.this, "Signo zodiacal:Libra", Toast.LENGTH_SHORT).show();
                                                                                }else{
                                                                                    if((month+1)==10 & day>22){
                                                                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.tsze), Toast.LENGTH_SHORT).show();
                                                                                        //Toast.makeText(MainActivity.this, "Signo zodiacal:Escorpio", Toast.LENGTH_SHORT).show();
                                                                                    }else{
                                                                                        if((month+1)==11 & day<=22){
                                                                                            Toast.makeText(MainActivity.this, getResources().getString(R.string.tsze), Toast.LENGTH_SHORT).show();
                                                                                            //Toast.makeText(MainActivity.this, "Signo zodiacal:Escorpio", Toast.LENGTH_SHORT).show();
                                                                                        }else{
                                                                                            if((month+1)==11 & day>22){
                                                                                                Toast.makeText(MainActivity.this, getResources().getString(R.string.tszsa), Toast.LENGTH_SHORT).show();
                                                                                                //Toast.makeText(MainActivity.this, "Signo zodiacal:Sagitario", Toast.LENGTH_SHORT).show();
                                                                                            }else{
                                                                                                if((month+1)==12 & day<=21){
                                                                                                    Toast.makeText(MainActivity.this, getResources().getString(R.string.tszsa), Toast.LENGTH_SHORT).show();
                                                                                                    //Toast.makeText(MainActivity.this, "Signo zodiacal:Sagitario", Toast.LENGTH_SHORT).show();
                                                                                                }else{
                                                                                                    if((month+1)==12 & day>21){
                                                                                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.tszcap), Toast.LENGTH_SHORT).show();
                                                                                                        //Toast.makeText(MainActivity.this, "Signo zodiacal:Capricornio", Toast.LENGTH_SHORT).show();
                                                                                                    }else{
                                                                                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.tfe), Toast.LENGTH_SHORT).show();
                                                                                                        //Toast.makeText(MainActivity.this,getResources().getString(R.string.tfe), Toast.LENGTH_SHORT).show();
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }

                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}