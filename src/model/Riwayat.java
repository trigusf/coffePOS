package model;

import java.util.Date;

public class Riwayat {

    private int idTransaksi;
    private int idKasir;
    private String username;
    private double bayar;
    private double total;
    private double kembali;
    private Date tanggal;

    public Riwayat(int idTransaksi, int idKasir, double bayar, double total, double kembali, Date tanggal){
        this.idTransaksi = idTransaksi;
        this.idKasir = idKasir;
        this.bayar = bayar;
        this.total = total;
        this.kembali = kembali;
        this.tanggal = tanggal;
    }

    public Riwayat(int idTransaksi, String username, double bayar, double total, double kembali, Date tanggal){
        this.idTransaksi = idTransaksi;
        this.username = username;
        this.bayar = bayar;
        this.total = total;
        this.kembali = kembali;
        this.tanggal = tanggal;
    }

    public int getIdTransaksi(){
        return idTransaksi;
    }
    public int getIdKasir(){
        return idKasir;
    }
    public String getUsername(){
        return username;
    }
    public double getBayar(){
        return bayar;
    }
    public double getTotal(){
        return total;
    }
    public double getKembali(){
        return kembali;
    }
    public Date getTanggal(){
        return tanggal;
    }

}
