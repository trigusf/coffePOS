package dao;

import database.DBConnection;
import model.DetailTransaksi;
import model.Riwayat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class riwayatDAO {

    Connection koneksi = DBConnection.getConnection();

//    query tampilan riwayat
    public List<Riwayat> getAllRiwayat(){
        List<Riwayat> list = new ArrayList<>();

        String query = "SELECT transaction.*, users.username FROM transaction JOIN users ON transaction.id_user = users.id_user";

        try(PreparedStatement ps = koneksi.prepareStatement(query);
        ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                Riwayat transaksi = new Riwayat(
                        rs.getInt("id_transaction"),
                        rs.getString("username"),
                        rs.getDouble("total"),
                        rs.getDouble("bayar"),
                        rs.getDouble("kembali"),
                        rs.getDate("tgl_transaction")
                );
                list.add(transaksi);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;


    }
//   end of query tampilan riwayat

//    query tampilan detail riwayat / struk

    public List<DetailTransaksi> detailTransaksi(int idTransaksi){
        List<DetailTransaksi> list = new ArrayList<>();

        String query = "SELECT detail_transaction.*, product.nama_product FROM detail_transaction JOIN product ON detail_transaction.id_product = product.id_product WHERE detail_transaction.id_transaction = ?";

        try(PreparedStatement ps = koneksi.prepareStatement(query);){
            ps.setInt(1, idTransaksi);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DetailTransaksi detail = new DetailTransaksi(
                            rs.getString("nama_product"),
                            rs.getDouble("harga"),
                            rs.getInt("qty"),
                            rs.getDouble("subtotal"),
                            rs.getInt("id_transaction")
                    );
                    list.add(detail);
                }
                return list;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

//    end of query tampilan detail riwayat / struk


}
