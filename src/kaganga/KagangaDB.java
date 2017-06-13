/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaganga;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aulia
 */
public final class KagangaDB {
    
    private String dbURL;
    private Connection conn;
    private Statement statement;

    public KagangaDB() {
        setDB("kaganga.sqlite");
    }
    
    public KagangaDB(String dbName) {
        setDB(dbName);
    }

    public void setDB(String dbName) {
        String path = new File("src\\"+getClass().getPackage().getName()+"\\"+dbName).getAbsolutePath();
        this.dbURL = "jdbc:sqlite:" + path;
    }
    
    public Connection connect() {
        try {
            this.conn = DriverManager.getConnection(this.dbURL);
            if(conn != null){
//                System.out.println("Terhubung");
                statement = conn.createStatement();
                statement.setQueryTimeout(30);
            }
            else System.out.println("Gagal");
        } catch (SQLException ex) {
            Logger.getLogger(KagangaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
    
    public void disconnect(){
        try {
            if(conn != null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KagangaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int insert(String data, int target){

        int id = -1;        
        try {
            String sql = "insert into data_latih (data, target) values ('"+data+"', '"+target+"');";
            id = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(KagangaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public ResultSet getAll(){
        try {
            String sql = "select * from data_latih";
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(KagangaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public int countAll(){
        try {
            String sql = "select count(*) as rows from data_latih";
            ResultSet rs = statement.executeQuery(sql);
            return rs.getInt(0);
        } catch (SQLException ex) {
            Logger.getLogger(KagangaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }    

    public ResultSet getTarget(){
        try {
            String sql = "select distinct(target) from data_latih";
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(KagangaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
//    public static void main(String[] args) {
//        try {
//            KagangaDB db = new KagangaDB();
//            db.connect();
//            db.insert("12646467612", 4);
//            
//            ResultSet rs = db.getAll();
//            while(rs.next())
//            {
//                String id   = rs.getString("id");
//                String data = rs.getString("data");
//                String targ = String.format("%5s", Integer.toBinaryString(rs.getInt("target"))).replace(' ', '0');
//                System.out.println(id+" | " + data +" | "+ targ);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(KagangaDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}
