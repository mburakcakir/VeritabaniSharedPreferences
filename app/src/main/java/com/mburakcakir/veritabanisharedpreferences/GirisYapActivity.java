package com.mburakcakir.veritabanisharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GirisYapActivity extends AppCompatActivity {

    EditText kullaniciAdi, sifre;
    OturumDuzenle oturumDuzenle;
    Button girisYap;
    KullaniciVeritabani kullaniciVeritabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);


        kullaniciAdi = findViewById(R.id.kullaniciAdi);
        sifre = findViewById(R.id.sifre);
        girisYap = findViewById(R.id.girisYap);
        oturumDuzenle = new OturumDuzenle(GirisYapActivity.this);
        kullaniciVeritabani = new KullaniciVeritabani(GirisYapActivity.this);

        if (oturumDuzenle.kullaniciGirisYapmismi() == true) {
            Intent intent = new Intent(GirisYapActivity.this, AnaSayfaActivity.class);
            startActivity(intent);
        }

        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bilgileriKontrolEt();
            }
        });
    }

    void bilgileriKontrolEt() {
        String kullaniciAdiAl = kullaniciAdi.getText().toString();
        String sifreAl = sifre.getText().toString();

        Boolean kullaniciVarmi = kullaniciVeritabani.kullaniciyiKontrolEt(kullaniciAdiAl);

        if (kullaniciVarmi == true) {
            oturumDuzenle.oturumBaslat(kullaniciAdiAl, sifreAl);
            Intent intent = new Intent(GirisYapActivity.this, AnaSayfaActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Lütfen kayıt olunuz.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GirisYapActivity.this, KayitOlActivity.class);
            startActivity(intent);
        }
    }
}
