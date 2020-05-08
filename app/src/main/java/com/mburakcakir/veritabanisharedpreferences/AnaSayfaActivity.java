package com.mburakcakir.veritabanisharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AnaSayfaActivity extends AppCompatActivity {

    OturumDuzenle oturumDuzenle;
    TextView hosgeldin;
    Button cikisYap, hesabiKaldir, kullanicilariGoster;
    KullaniciVeritabani kullaniciVeritabani;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        hosgeldin = findViewById(R.id.hosgeldin);
        cikisYap = findViewById(R.id.cikisYap);
        hesabiKaldir = findViewById(R.id.hesabiKaldir);
        oturumDuzenle = new OturumDuzenle(AnaSayfaActivity.this);
        kullaniciVeritabani = new KullaniciVeritabani(AnaSayfaActivity.this);
        kullanicilariGoster = findViewById(R.id.kullanicilariGoster);
        listView = findViewById(R.id.listview);

        final String kullaniciAdiAl = String.valueOf(oturumDuzenle.kullaniciDetaylariniAl().get(OturumDuzenle.KULLANICI_ADI));
        String sifreAl = String.valueOf(oturumDuzenle.kullaniciDetaylariniAl().get(OturumDuzenle.SIFRE));

        hosgeldin.setText("Kullanıcı adı : " + kullaniciAdiAl + " Şifre : " + sifreAl);

        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oturumDuzenle.oturumKapat();
                Intent intent = new Intent(AnaSayfaActivity.this, GirisYapActivity.class);
                startActivity(intent);
                finish();
            }
        });

        hesabiKaldir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullaniciVeritabani.kullaniciyiSil(kullaniciAdiAl);
                oturumDuzenle.oturumKapat();
                Intent intent = new Intent(AnaSayfaActivity.this, KayitOlActivity.class);
                startActivity(intent);
                finish();
            }
        });

        kullanicilariGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> kullaniciBilgileri = kullaniciVeritabani.kullaniciIsimleriniGetir();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (AnaSayfaActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, kullaniciBilgileri);
                listView.setAdapter(adapter);
            }
        });

    }
}

