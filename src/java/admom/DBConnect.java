/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author felipelondono
 */
public class DBConnect {
    private Connection con;
    private Statement sta;
    private ResultSet res;
    public DBConnect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdDB","root","");
            sta = con.createStatement();
        }catch(Exception ex){         
        }
    }
    
    
    public ArrayList<String> ListChannelsUser(ArrayList<String> lista, String User){
        ListChannels(lista);
        System.out.println(lista);
        ArrayList<String> sublist = new ArrayList();
        for(int i=0;i<lista.size();i++){
            String aux1 = lista.get(i);
            String query = "SELECT * FROM `Users` WHERE " +aux1
                    +" IS NOT NULL AND Username='"+User+"'";
            try {
                res = sta.executeQuery(query);
                while(res.next()){
                    String aux = res.getString(i+3);
                    sublist.add(aux1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return sublist;
    }

    
    public ArrayList<String> ListChannels(ArrayList<String> list){
        String query = "DESCRIBE `Users`";
        try {
            
            res = sta.executeQuery(query);
            while(res.next()){
                String aux = res.getString(1);
                if(!(aux.equals("id")) && !(aux.equals("Username"))){
                    list.add(res.getString(1));
                }   
            }
        } catch (SQLException ex) {
            
        }
        return list;
    }
    
    public boolean CreateUser(String user){
        String users = "SELECT Username FROM Users WHERE Username='"+user+"'";
        boolean existe = false;
        boolean dio = false;
        try {
            res = sta.executeQuery(users);
            while(res.next()){
                res.getString(1);
                existe = true;
            }
            if(!existe){
                String query = "INSERT INTO Users (username) VALUES ('"+user+"')";
                try {
                    sta.executeUpdate(query);
                    dio = true;
                } catch (SQLException ex) {
                    dio = false;
                }
            }else{
                System.out.println("El usuario ya existe");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dio;
    }
    
    boolean CreateChannel(String channelName){
        ArrayList<String> list = new ArrayList();
        list = ListChannels(list);
        boolean a = false;
        boolean existe = false;
        for(int i=0;i<list.size();i++){
            if(channelName.equals(list.get(i))){
                existe = true;
            }
        }
        if(existe){
            a = true;
        }else{
            String query = "ALTER TABLE Users ADD " + channelName + " boolean" ;
            try {
                sta.executeUpdate(query);
                a = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                a = false;
            }
        }
        return a;
    }
    
        boolean subscribe(String channel, String user){
        String query = "UPDATE Users SET "+channel+"= '1' WHERE Username='"+user+"'";
        boolean a = false;
        try {
            sta.executeUpdate(query);
            a = true;
        } catch (SQLException ex) {
            a = false;
            System.out.println(ex.getMessage());
        }
        return a;
    }

}
