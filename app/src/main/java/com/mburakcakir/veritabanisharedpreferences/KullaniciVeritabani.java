package com.mburakcakir.veritabanisharedpreferences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class KullaniciVeritabani extends SQLiteOpenHelper {

    public static final String VERITABANI_ADI = "t3";
    public static final int VERITABANI_VERSIYONU = 1;
    public static final String TABLO_ADI = "kullanici";

    public static final String BASLIK_ID = "ID";
    public static final String BASLIK_ISIM = "isim";
    public static final String BASLIK_KULLANICI_ADI = "kullaniciadi";
    public static final String BASLIK_EMAIL = "email";
    public static final String BASLIK_SIFRE = "sifre";


    String veritabaniOlustur = "CREATE TABLE " + TABLO_ADI
            + " ( " + BASLIK_ID + " INTEGER, "
            + BASLIK_ISIM + " TEXT, "
            + BASLIK_KULLANICI_ADI + " TEXT, "
            + BASLIK_EMAIL + " TEXT, "
            + BASLIK_SIFRE + " TEXT)";


    public KullaniciVeritabani(Context context) {
        super(context, VERITABANI_ADI, null, VERITABANI_VERSIYONU);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(veritabaniOlustur);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void kullaniciEkle(KullaniciBilgileri kullaniciBilgileri) {
        SQLiteDatabase veritabani = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BASLIK_ID, kullaniciBilgileri.getID());
        contentValues.put(BASLIK_ISIM, kullaniciBilgileri.getIsim());
        contentValues.put(BASLIK_KULLANICI_ADI, kullaniciBilgileri.getKullaniciAdi());
        contentValues.put(BASLIK_EMAIL, kullaniciBilgileri.getEmail());
        contentValues.put(BASLIK_SIFRE, kullaniciBilgileri.getSifre());

        veritabani.insert(TABLO_ADI, null, contentValues);
        veritabani.close();
    }

    public ArrayList<String> kullaniciIsimleriniGetir() {
        SQLiteDatabase veritabani = this.getReadableDatabase();
        ArrayList<String> kullaniciBilgileriList = new ArrayList<String>();

        String sorgu = "SELECT * FROM " + TABLO_ADI;
        Cursor cursor = veritabani.rawQuery(sorgu, null);

        while (cursor.moveToNext()) {
            kullaniciBilgileriList.add(cursor.getString(2));
        }

        cursor.close();
        return kullaniciBilgileriList;
    }

    public void kullaniciyiSil(String kullaniciAdi) {
        SQLiteDatabase veritabani = this.getWritableDatabase();
        String sorgu = BASLIK_KULLANICI_ADI + " = ? ";
        String[] argumanlar = new String[]{kullaniciAdi};
        veritabani.delete(TABLO_ADI, sorgu, argumanlar);
    }

    public void kullaniciyiGuncelle(KullaniciBilgileri kullaniciBilgileri) {
        SQLiteDatabase veritabani = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BASLIK_ISIM, kullaniciBilgileri.getIsim());
        contentValues.put(BASLIK_KULLANICI_ADI, kullaniciBilgileri.getKullaniciAdi());
        contentValues.put(BASLIK_EMAIL, kullaniciBilgileri.getEmail());
        contentValues.put(BASLIK_SIFRE, kullaniciBilgileri.getSifre());

        String sorgu = BASLIK_ID + " = ?";
        String[] argumanlar = new String[]{Integer.toString(kullaniciBilgileri.getID())};
        veritabani.update(TABLO_ADI, contentValues, sorgu, argumanlar);
    }

    public Boolean kullaniciyiKontrolEt(String kullaniciAdi) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlCumlesi = "SELECT * FROM " + TABLO_ADI + " WHERE " + BASLIK_KULLANICI_ADI + "= '" + kullaniciAdi + "'";
        Cursor cursor = db.rawQuery(sqlCumlesi, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
