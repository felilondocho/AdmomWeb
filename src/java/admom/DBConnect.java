/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admom;

import java.sql.*;
import java.util.ArrayList;



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
    
    public ArrayList<String> ListChannels(ArrayList<String> list){
        String query = "DESCRIBE `Users`";
        try {
            res = sta.executeQuery(query);
            while(res.next()){
                list.add(res.getString(1));
            }
        } catch (SQLException ex) {
            
        }
        return list;
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
    
    
}
