package com.example.athaya.athayaputrislavia_1202150252_studycse5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addtodo extends AppCompatActivity {
    //mendeklarasikan objek yang akan digunakan
    private EditText ToDo, Description, Priority;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtodo);

        //memanggil id dari objek yang sudah di deklarasikan
        ToDo = findViewById(R.id.edt_Todo);
        Description = findViewById(R.id.edt_Desc);
        Priority = findViewById(R.id.edt_Priority);
        db = new Database(this);
    }

    @Override
    public void onBackPressed() {

        //untuk pindah ke class
        Intent intent = new Intent(addtodo.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void addTodo(View view) {
        //menambahkan isi dari list
        if (db.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //menampilkan toast saat data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "To Do List Ditambahkan !", Toast.LENGTH_SHORT).show();
            //untuk pindah class
            startActivity(new Intent(addtodo.this, MainActivity.class));
            this.finish();
        }else {
            //jika ada edit text yang kosong
            Toast.makeText(this, "List tidak boleh kosong", Toast.LENGTH_SHORT).show();
            //mereset semua edit text menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }

}
