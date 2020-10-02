/*
* Notlar:

* */



package com.aylingunes.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    // SQL işlemlerinde hata kodda görünmeyebilir o nedenle try içinde yapalım
    try {
        // veri tabanı açıyoruz
        SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
        // tablo luştur eğer yoksa; // id iiçin int değil integer demelisin yoksa sql görmüyor
        database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR,age INTEGER)");
        // değer ekleme
        // değerleri şimdilik yorum haline getiriyoruz, firstrun'da zaten veritabanına eklendikleri için
        //database.execSQL("INSERT INTO musicians (name,age) VALUES ('James', 50)");
        //database.execSQL("INSERT INTO musicians (name,age) VALUES ('Lars', 60)");
        //database.execSQL("INSERT INTO musicians (name,age) VALUES ('Kirk',55)");


        //güncelleme*****
        //database.execSQL("UPDATE musicians SET age = 61 WHERE name= 'Lars'");

        //silme***
        //database.execSQL("DELETE FROM musicians WHERE id=2");


        // veritabanından değer okuma rawQuery sorgu için kullanılır null filtreleme istemiyorum demek
       // Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'James'",null);
        Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);

        // cursore hangi sutuna gideceğini söylemek için;
        int nameIx= cursor.getColumnIndex("name");
        int ageIx = cursor.getColumnIndex("age");
        int idIx = cursor.getColumnIndex("id");
        while(cursor.moveToNext()) {
            // cursor ilerleyebildiği kadar gitsin o sırada bu işlemleri yapsın
            System.out.println("Name: "+cursor.getString(nameIx));
            System.out.println("Age: "+cursor.getInt(ageIx));
            System.out.println("id: "+cursor.getInt(idIx));
        } cursor.close(); // cursorun durması gerekiyor bir noktada, bu nedenle close et


    }catch(Exception e){
        e.printStackTrace();
    }

    }
}