package com.example.athaya.athayaputrislavia_1202150252_studycse5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //mendeklarasikan objek yang akan digunakan
    private Database dtBase;
    private RecyclerView rcView;
    private Adapter adapter;
    private ArrayList<AddData> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Memanggil id dari recycler view
        rcView = findViewById(R.id.rec_view);
        //membuat araylist baru
        data_list = new ArrayList<>();
        //membuat database baru
        dtBase = new Database(this);
        //memanggil readdata
        dtBase.readdata(data_list);

        //membuat shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        adapter = new Adapter(this,data_list, color);

        //mengatur ukuran recycler view
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        //mendeklarasikan adapter untuk recycler view
        rcView.setAdapter(adapter);

        //mendeklarasikan objek untuk menghapus list
        hapusgeser();
    }

    //untuk menghapus item pada to do list
    public void hapusgeser(){
        //membuat touch helper untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);

                //jika item di swipe ke arah kiri
                if(direction==ItemTouchHelper.LEFT){
                    //menghapus item yang dipilih
                    if(dtBase.removedata(current.getTodo())){
                        adapter.deleteData(position);

                        //membuat snack bar dan pemberitahuan bahwa item sudah terhapus dengan durasi 2 sekon
                        Snackbar.make(findViewById(R.id.coordinator), "List Telah Terhapus", 2000).show();
                    }
                }
            }
        };
        //membuat itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rcView);
    }

    //saat menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //menjalankan method saat item terpilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //memanggil id dari item
        int id = item.getItemId();
        //jika memilih setting
        if (id==R.id.action_settings){
            //pindah ke class activity_setting
            Intent a = new Intent(MainActivity.this, activity_setting.class);
            startActivity(a);
            //menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }

    //ketika tombol add di klik
    public void addlist(View view) {
        //pindah ke class addtodo
        Intent intent = new Intent(MainActivity.this, addtodo.class);
        startActivity(intent);
    }
}
