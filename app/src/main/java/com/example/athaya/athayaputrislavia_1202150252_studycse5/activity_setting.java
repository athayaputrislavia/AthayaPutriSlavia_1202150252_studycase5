package com.example.athaya.athayaputrislavia_1202150252_studycse5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Athaya on 3/24/2018.
 */

public class activity_setting extends AppCompatActivity {
    //mendeklrasikan variabel yang digunakan
    private TextView warna;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        setTitle("Settings");

        //membuat alert dialog baru
        alert = new AlertDialog.Builder(this);

        //menginisialisasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        sharedpref = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);
        //mengakses text view pada layout
        warna = findViewById(R.id.shapecolor);
        //mengatur warna dengan warna id yang terpilih
        warna.setText(getShapeColor(colorid));
    }

    //apabila tombol back di tekan
    @Override
    public void onBackPressed() {
        //pindah class
        Intent intent = new Intent(activity_setting.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //mendapatkan string warna yang akan digunakan untuk mengubah shape color
    public String getShapeColor(int i){
        if (i==R.color.pink){
            return "Pink";
        }else if (i==R.color.cyan){
            return "Cyan";
        }else if (i==R.color.kuning){
            return "Kuning";
        }else if (i==R.color.tomato){
            return "Tomato";
        }else if (i==R.color.lime){
            return "Lime";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.pink){
            return R.id.pink;
        }else if (i==R.color.kuning){
            return R.id.kuning;
        }else if (i==R.color.cyan){
            return R.id.cyan;
        }else if (i==R.color.lime){
            return R.id.lime;
        }else if (i==R.color.tomato){
            return R.id.tomato;
        }else{
            return R.id.white;
        }
    }

    public void pilihWarna(View view) {
        //set title dari alert dialog menjadi Shape Color
        alert.setTitle("Shape Color");
        //membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.pilihan_warna, null);
        //menampilkan view yang telah dibuat
        alert.setView(view1);

        //mengakses radio group
        final RadioGroup radG = view1.findViewById(R.id.radio_color);
        radG.check(getColorid(colorid));

        //ketika menekan Ok pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.pink:
                        colorid = R.color.pink;
                        break;
                    case R.id.kuning:
                        colorid = R.color.kuning;
                        break;
                    case R.id.cyan:
                        colorid = R.color.cyan;
                        break;
                    case R.id.lime:
                        colorid = R.color.lime;
                        break;
                    case R.id.tomato:
                        colorid = R.color.tomato;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //membuat shape color menjadi color id yang dipilih
                warna.setText(getShapeColor(colorid));
                //menaruh shared preference
                sharedpref.putInt("Colourground", colorid);
                sharedpref.commit();
            }
        });

        //saat menekan Cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //menampilkan alert dialog
        alert.create().show();
    }


}
