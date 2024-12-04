package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;
import entity.Grado;
import util.FechaUtil;
import util.MySqlDBConexion;

public class ModelAutor {
	
	
	public int insertarAutor( Autor obj) {
		
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {

			conn = MySqlDBConexion.getConexion();
			
			String sql = "INSERT INTO autor (nombres, apellidos, fechaNacimiento, fechaActualizacion, telefono, fechaRegistro, estado, idGrado) " +
                    	 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setDate(3, obj.getFechaNacimiento());
			pstm.setTimestamp(4, new Timestamp(System.currentTimeMillis())); 
			pstm.setString(5, obj.getTelefono());
			pstm.setTimestamp(6, new Timestamp(System.currentTimeMillis())); 
			pstm.setShort(7, (short) 1);
			pstm.setInt(8, obj.getIdGrado());

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
	
	public List<Autor> listaAutorComplejo(String nombres, String telefono,Date fdesde,Date fhasta) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Autor> lstSalida = new ArrayList<Autor>();
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select a.*, g.descripcion from autor a inner join grado_autor g on g.idGrado = a.idGrado "
						+ "where (a.nombres like ? or a.apellidos like ? ) and "
						+ "( ? = '' or a.telefono = ?) and "
						+ "(a.fechaNacimiento >= ? and a.fechaNacimiento <= ?)";
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, nombres);
			pstm.setString(3, telefono);
			pstm.setString(4, telefono);
			pstm.setDate(5, fdesde);
			pstm.setDate(6, fhasta);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Autor objAutor = null;
			Grado objGrado = null;
			while(rs.next()) {
				objAutor = new Autor();
				objAutor.setIdAutor(rs.getInt(1));
				objAutor.setNombres(rs.getString(2));
				objAutor.setApellidos(rs.getString(3));
				objAutor.setFechaNacimiento(rs.getDate(4));
				objAutor.setFechaFormateada(FechaUtil.getFechaFormateadaYYYYMMdd(rs.getDate(4)));
				objAutor.setFechaActualizacion(rs.getTimestamp(5));
				objAutor.setTelefono(rs.getString(6));
				objAutor.setFechaRegistro(rs.getTimestamp(7));
				objAutor.setEstado(rs.getInt(8));
				
				System.out.println("111111111111111111111111"+objAutor.getFechaFormateada());
				
		
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(9));
				objGrado.setDescripcion(rs.getString(10));
				objAutor.setGrado(objGrado);  /// preguntar al profesor 
				lstSalida.add(objAutor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {}
		}
		return lstSalida;
	}
	
	
	
	
	
	
	
}
