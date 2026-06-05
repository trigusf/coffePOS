package dao;

import database.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productDAO {

//    tambah data
    public boolean tambahProduk(Product product){
        String query = "INSERT INTO product (nama_product, harga_product, stock, category) VALUES (?,?,?,?) ";

        try(Connection koneksi = DBConnection.getConnection();
            PreparedStatement ps = koneksi.prepareStatement(query)){
            ps.setString(1, product.getNamaProduk());
            ps.setDouble(2, product.getHarga());
            ps.setInt(3, product.getStock());
            ps.setString(4, product.getKategori());

            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

//    end of tambah data

//    edit data

        public boolean editProduk(Product product){
            String query = "UPDATE product SET nama_product = ?, harga_product = ?, stock = ?, category = ? WHERE id_product = ?";

            try(Connection koneksi = DBConnection.getConnection();
                PreparedStatement ps = koneksi.prepareStatement(query)){
                ps.setString(1, product.getNamaProduk());
                ps.setDouble(2, product.getHarga());
                ps.setInt(3, product.getStock());
                ps.setString(4, product.getKategori());
                ps.setInt(5, product.getIdProduk());

                return ps.executeUpdate() > 0;
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }

//    end of edit data


//    hapus data

    public boolean deleteProduk(int id){
        String query = "DELETE FROM product WHERE id_product = ? ";

        try(Connection koneksi = DBConnection.getConnection();
            PreparedStatement ps = koneksi.prepareStatement(query);
        ){
            ps.setInt(1, id);

            return  ps.executeUpdate() > 0;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    end of hapus data

//    tampil data

    public List<Product> getAllProduk(){
        List<Product> list = new ArrayList<>();

        String query = "SELECT * FROM product";

        try(
            Connection koneksi = DBConnection.getConnection();

            PreparedStatement ps = koneksi.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                Product produk = new Product(
                        rs.getInt("id_product"),
                        rs.getString("nama_product"),
                        rs.getDouble("harga_product"),
                        rs.getInt("Stock"),
                        rs.getString("category")
                );
                list.add(produk);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

//    end of tampil data


}
