package model;

public class Keranjang {
    private Produk produk;
    private int qty;

    public Keranjang(Produk produk, int qty){
        this.produk = produk;
        this.qty = qty;
    }

    public Produk getProduk(){
        return  produk;
    }

    public int getQty(){
        return qty;
    }

    public void setQty(int qty){
        this.qty = qty;
    }

    public String getNamaProduk(){
        return produk.getNamaProduk();
    }

    public double getHarga(){
        return produk.getHarga();
    }

    public double getSubtotal(){
        return produk.getHarga() * qty;
    }
}
