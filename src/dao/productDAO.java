package dao;

import database.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class productDAO {

    public boolean tambahProduk(Product product){
        String query = "INSERT INTO product (nama_product, harga_product, stock, category) VALUES (?,?,?,?) ";

        try(Connection koneksi = DBConnection.getConnection();
            PreparedStatement ps = koneksi.prepareStatement(query)){
            ps.setString(1, product.getNamaProduk());
            ps.setDouble(2, product.getHarga());
            ps.setInt(3, product.getStock());
            ps.setString(4, product.getCategory());

            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
