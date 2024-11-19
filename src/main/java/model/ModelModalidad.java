package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Modalidad;
import util.MySqlDBConexion;

public class ModelModalidad  {


   
    public List<Modalidad> lista() {
        List<Modalidad> salida = new ArrayList<Modalidad>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = MySqlDBConexion.getConexion();
            String sql = "select * from Modalidad";
            pstm = conn.prepareStatement(sql);
            System.out.println("sql ==>"+pstm+"\n");
            rs = pstm.executeQuery();

            Modalidad obj =null;

            while (rs.next()) {
                obj = new Modalidad();
                obj.setIdModalidad(rs.getInt(1));
                obj.setDescripcion(rs.getString(2));
                salida.add(obj);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if (pstm != null) {pstm.close();}
                if (conn != null) {conn.close();}
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return salida;
    }
}
