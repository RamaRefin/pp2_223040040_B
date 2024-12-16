/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import org.apache.ibatis.annotations.*;
import java.util.List;
/**
 *
 * @author thega
 */
public interface ObatMapper {
    @Select("SELECT * FROM obat")
    List<Obat> getAllObats();

    @Insert("INSERT INTO obat (nama, jenis, harga, stok) VALUES (#{nama}, #{jenis}, #{harga}, #{stok})")
    void insertObat(Obat obat);

    @Update("UPDATE obat SET nama = #{nama}, jenis = #{jenis}, harga = #{harga}, stok = #{stok} WHERE id = #{id}")
    void updateObat(Obat obat);

    @Delete("DELETE FROM obat WHERE id = #{id}")
    void deleteObat(int id);
}
