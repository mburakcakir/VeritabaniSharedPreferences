package com.mburakcakir.veritabanisharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KayitOlActivity extends AppCompatActivity {

    EditText isim, kullaniciAdi, email, sifre;
    Button kayitOl, girisYap;
    KullaniciVeritabani kullaniciVeritabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        isim = findViewById(R.id.isim);
        kullaniciAdi = findViewById(R.id.kullaniciAdi);
        email = findViewById(R.id.email);
        sifre = findViewById(R.id.sifre);

        kayitOl = findViewById(R.id.kayitOl);
        girisYap = findViewById(R.id.girisYap);
        kullaniciVeritabani = new KullaniciVeritabani(KayitOlActivity.this);

        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KayitOlActivity.this, GirisYapActivity.class);
                startActivity(intent);
            }
        });

        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bilgileriKontrolEt();
            }
        });

    }

    void bilgileriKontrolEt() {
        String isimAl = isim.getText().toString();
        String kullaniciAdiAl = kullaniciAdi.getText().toString();
        String emailAl = email.getText().toString();
        String sifreAl = sifre.getText().toString();

        Boolean kullaniciVarmi = kullaniciVeritabani.kullaniciyiKontrolEt(kullaniciAdiAl);

        if (kullaniciVarmi == false) {
            KullaniciBilgileri kullaniciBilgileri = new KullaniciBilgileri();

            kullaniciBilgileri.setIsim(isimAl);
            kullaniciBilgileri.setKullaniciAdi(kullaniciAdiAl);
            kullaniciBilgileri.setEmail(emailAl);
            kullaniciBilgileri.setSifre(sifreAl);

            kullaniciVeritabani.kullaniciEkle(kullaniciBilgileri);

            Intent intent = new Intent(KayitOlActivity.this, GirisYapActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Kullanıcı zaten kayıtlı.", Toast.LENGTH_SHORT).show();
        }
    }
}
