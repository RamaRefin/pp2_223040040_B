/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.MyBatisUtil; 
import model.ObatMapper;
import org.apache.ibatis.session.SqlSession; 
import view.ObatsView;
import controller.ObatsController;
/**
 *
 * @author thega
 */
public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession(); 
        ObatMapper mapper = session.getMapper(ObatMapper.class);
        
        ObatsView view = new ObatsView(); 
        new ObatsController(view, mapper);
        
        view.setVisible(true);
    }
}

