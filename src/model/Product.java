package model;

public class Product {
    private int idProduk;
    private String namaProduk;
    private double harga;
    private int stock;
    private String kategori;

    public Product(String namaProduk, double harga, int stock, String kategori){
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stock = stock;
        this.kategori = kategori;
    }

    public Product(int idProduk, String namaProduk, double harga, int stock, String kategori){
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stock = stock;
        this.kategori = kategori;
    }


    public int getIdProduk(){return idProduk; }

    public String getNamaProduk(){
        return namaProduk;
    }

    public double getHarga(){
        return harga;
    }

    public int getStock(){
        return stock;
    }

    public String getKategori(){
        return kategori;
    }


}
