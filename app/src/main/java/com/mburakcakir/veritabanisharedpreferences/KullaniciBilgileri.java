package com.mburakcakir.veritabanisharedpreferences;

public class KullaniciBilgileri {

    int ID;
    String isim;
    String kullaniciAdi;
    String email;
    String sifre;

    public KullaniciBilgileri(String isim, String kullaniciAdi, String email, String sifre) {
        this.isim = isim;
        this.kullaniciAdi = kullaniciAdi;
        this.email = email;
        this.sifre = sifre;
    }

    public KullaniciBilgileri() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
