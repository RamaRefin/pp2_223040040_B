package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.MySqlConnection;
import model.Biodata;
import model.JenisMember;

public class BiodataDao {
    public int insert(Biodata biodata) {
        int result = -1;

        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO detail (id, nama, jenis_kelamin, alamat) VALUES (?, ?, ?, ?)");
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getJenis_kelamin());
            statement.setString(4, biodata.getAlamat());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE detail SET nama = ?, jenis_kelamin = ?, alamat = ?  WHERE id = ?");
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getJenis_kelamin());
            statement.setString(3, biodata.getAlamat());
            statement.setString(4, biodata.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM detail WHERE id = ?");
            statement.setString(1, biodata.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Biodata> findAll() {
        List<Biodata> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT detail.id, detail.nama, detail.jenis_kelamin, detail.alamat, " +
                    "jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama " +
                    "FROM detail " +
                    "JOIN jenis_member ON jenis_member.id = detail.jenis_member_id")) {
    
                // Fetch data from ResultSet
                while (resultSet.next()) {
                    // Create Biodata object
                    Biodata biodata = new Biodata();
                    biodata.setId(resultSet.getString("id"));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setJenis_kelamin(resultSet.getString("jenis_kelamin"));
                    biodata.setAlamat(resultSet.getString("alamat"));
    
                    // Create JenisMember object and associate it with Biodata
                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(resultSet.getString("jenis_member_id"));
                    jenisMember.setNama(resultSet.getString("jenis_member_nama"));
    
                    // Set the JenisMember in Biodata
                    biodata.setJenisMember(jenisMember);
    
                    // Add Biodata to the list
                    list.add(biodata);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
    