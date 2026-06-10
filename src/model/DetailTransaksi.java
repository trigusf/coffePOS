package model;

public class DetailTransaksi {
    private int idDetailTransaksi;
    private int idTransaksi;
    private int idProduk;
    private String namaProduk;
    private double harga;
    private int qty;
    private double subtotal;

    public DetailTransaksi(String namaProduk, double harga, int qty, double subtotal, int idTransaksi){
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.qty = qty;
        this.subtotal = subtotal;
        this.idTransaksi = idTransaksi;
    }

    public String getNamaProduk(){
        return namaProduk;
    }

    public double getHarga(){
        return harga;
    }

    public int getQty(){
        return qty;
    }

    public double getSubtotal(){
        return subtotal;
    }

    public int getIdTransaksi(){
        return  idTransaksi;
    }

}
