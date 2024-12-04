package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Pais;
import entity.Proveedor;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class ModelProveedor {

	public int insertarProveedor(Proveedor obj) {

		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		
		try {
            conn = MySqlDBConexion.getConexion();
            String sql = "insert into proveedor values(null,?,?,?,?,?,?,?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getRazonsocial());
			pstm.setString(2, obj.getRuc());
			pstm.setString(3, obj.getDireccion());
			pstm.setString(4, obj.getCelular());
			pstm.setString(5, obj.getContacto());
			pstm.setInt(6, obj.getEstado());
			pstm.setTimestamp(7, obj.getFechaRegistro());
			pstm.setTimestamp(8, obj.getFechaActualizacion());
			pstm.setInt(9, obj.getPais().getIdPais());

                
			System.out.println("SQL => " + pstm);

			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {}
		}
		return salida;
	}
		

	
	public List<Proveedor> listarProveedores() {
	    Connection conn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    List<Proveedor> lstSalida = new ArrayList<Proveedor>();

	    try {
	        conn = MySqlDBConexion.getConexion();

	        // Consulta para obtener todos los proveedores
	        String sql = "SELECT idProveedor, razonsocial, ruc, direccion, celular, contacto, estado, fechaRegistro, fechaActualizacion, idPais FROM proveedor";
	        pstm = conn.prepareStatement(sql);
	        
	        System.out.println("SQL => " + pstm);

	        rs = pstm.executeQuery();

	        Proveedor objProveedor;
	        while (rs.next()) {
	            objProveedor = new Proveedor();
	            objProveedor.setIdProveedor(rs.getInt("idProveedor"));
	            objProveedor.setRazonsocial(rs.getString("razonsocial"));
	            objProveedor.setRuc(rs.getString("ruc"));
	            objProveedor.setDireccion(rs.getString("direccion"));
	            objProveedor.setCelular(rs.getString("celular"));
	            objProveedor.setContacto(rs.getString("contacto"));
	            objProveedor.setEstado(rs.getInt("estado"));
	            objProveedor.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
	            objProveedor.setFechaActualizacion(rs.getTimestamp("fechaActualizacion"));
	            
	            // Asignamos el objeto Pais basado en el idPais, si es necesario
                Pais pais = new Pais();
                pais.setIdPais(rs.getInt("idPais"));
                objProveedor.setPais(pais);

	            
	            lstSalida.add(objProveedor);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstm != null) pstm.close();
	            if (conn != null) conn.close();
	        } catch (Exception e2) {}
	    }

	    return lstSalida;
	}




}






