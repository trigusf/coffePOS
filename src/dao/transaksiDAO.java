package dao;

import database.DBConnection;
import javafx.collections.ObservableList;
import model.Keranjang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class transaksiDAO {
    Connection koneksi = DBConnection.getConnection();

    public int insertTransaksi(int idUser, double total, double bayar, double kembali ){
        System.out.println(
                "masook"
        );
        int idTransaksi = 0;

            String query = "INSERT INTO `transaction` (id_user, total, bayar, kembali, tgl_transaction) VALUES (?, ?, ?, ?, NOW())";
        try{
            PreparedStatement ps = koneksi.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, idUser);
            ps.setDouble(2, total);
            ps.setDouble(3, bayar);
            ps.setDouble(4, kembali);

            int row  = ps.executeUpdate();

            System.out.println("row = " + row);

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                idTransaksi = rs.getInt(1);
                System.out.println("generated id = " + idTransaksi);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return idTransaksi;

    }

    public void insertDetail(int idTransaksi, ObservableList<Keranjang> keranjang){
        String query = "INSERT INTO `detail_transaction` (id_transaction, id_product, harga, qty, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = koneksi.prepareStatement(query);
            for (Keranjang item : keranjang){
                ps.setInt(1, idTransaksi);
                ps.setInt(2, item.getProduk().getIdProduk());
                ps.setDouble(3, item.getProduk().getHarga());
                ps.setInt(4, item.getQty());
                ps.setDouble(5, item.getSubtotal());
                ps.addBatch();
            }
            ps.executeBatch();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateStock(ObservableList<Keranjang> keranjang){
        String query = "UPDATE product SET stock = stock - ? WHERE id_product = ?";

        try{
            PreparedStatement ps = koneksi.prepareStatement(query);
            for (Keranjang item : keranjang){
                ps.setInt(1, item.getQty());
                ps.setInt(2, item.getProduk().getIdProduk());
                ps. addBatch();
            }
            ps.executeBatch();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
