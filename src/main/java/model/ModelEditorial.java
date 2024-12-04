package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Editorial;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class ModelEditorial {
	
	public int insertarEditorial(Editorial obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into editorial values(null,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getRazonSocial());
			pstm.setString(2, obj.getDireccion());
			pstm.setString(3, obj.getTelefono());
			pstm.setString(4, obj.getRuc());			
			pstm.setDate(5, obj.getFechaCreacion());
			pstm.setTimestamp(6, obj.getFechaRegistro());
			pstm.setTimestamp(7, obj.getFechaActualizacion());
			pstm.setInt(8, obj.getEstado());
			pstm.setInt(9, obj.getPais().getIdPais());
			//pstm.setString(10, obj.getFechaFormateada());

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
	
	public List<Editorial> listaXRazonSocial(String razonSocial) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Editorial> lstSalida = new ArrayList<Editorial>();
		try {
			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM editorial where razonSocial = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, razonSocial);
			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Editorial objEditorial;
			while (rs.next()) {
				objEditorial = new Editorial();
				objEditorial.setIdEditorial(rs.getInt(1));
				objEditorial.setRazonSocial(rs.getString(2));
				lstSalida.add(objEditorial);

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
			} catch (Exception e2) {
			}
		}
		return lstSalida;
	}
	
	public List<Editorial> listaXTelefonoIguales(String telefono) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Editorial> lstSalida = new ArrayList<Editorial>();

		try {

			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM editorial where telefono = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, telefono);
			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Editorial objEditorial;
			while (rs.next()) {
				objEditorial = new Editorial();
				objEditorial.setIdEditorial(rs.getInt(1));
				objEditorial.setTelefono(rs.getString(2));

				lstSalida.add(objEditorial);

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
			} catch (Exception e2) {
			}
		}
		return lstSalida;

	}
	
	public List<Editorial> listaXRUCIguales(String ruc) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Editorial> lstSalida = new ArrayList<Editorial>();

		try {

			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM editorial where ruc = ? ";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, ruc);

			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Editorial objEditorial;
			while (rs.next()) {
				objEditorial = new Editorial();
				objEditorial.setIdEditorial(rs.getInt(1));
				objEditorial.setRuc(rs.getString(2));

				lstSalida.add(objEditorial);

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
			} catch (Exception e2) {
			}
		}
		return lstSalida;

	}
	
	

}
