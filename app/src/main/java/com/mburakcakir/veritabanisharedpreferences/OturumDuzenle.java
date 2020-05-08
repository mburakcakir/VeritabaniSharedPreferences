package com.mburakcakir.veritabanisharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class OturumDuzenle {

    public static final String KULLANICI_ADI = "kullaniciadi";
    public static final String SIFRE = "sifre";
    public static final String PREF_ISIM = "t3";
    public static final String GIRIS_YAPMIS_MI = "girisyapmismi";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public OturumDuzenle(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_ISIM, 0);
        editor = sharedPreferences.edit();
    }

    public void oturumBaslat(String kullaniciAdi, String sifre) {
        editor.putBoolean(GIRIS_YAPMIS_MI, true);
        editor.putString(KULLANICI_ADI, kullaniciAdi);
        editor.putString(SIFRE, sifre);
        editor.commit();
    }

    public HashMap kullaniciDetaylariniAl() {
        HashMap kullanici = new HashMap();
        kullanici.put(KULLANICI_ADI, sharedPreferences.getString(KULLANICI_ADI, null));
        kullanici.put(SIFRE, sharedPreferences.getString(SIFRE, null));
        return kullanici;
    }

    public void oturumKapat() {
        editor.clear();
        editor.commit();
    }

    public Boolean kullaniciGirisYapmismi() {
        return sharedPreferences.getBoolean(GIRIS_YAPMIS_MI, false);
    }

}
