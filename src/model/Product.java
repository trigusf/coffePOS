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

    public void setNamaProduk(String namaProduk){
        this.namaProduk = namaProduk;
    }

    public void setHarga(double harga){
        this.harga = harga;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public void setKategori(String kategori){
        this.kategori = kategori;
    }

}
