package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Libro;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class ModelLibro {

	public int insertarLibro(Libro obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into libro values(null,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnio());
			pstm.setString(3, obj.getSerie());			
			pstm.setString(4, obj.getTema());		
			pstm.setTimestamp(5, obj.getFechaRegistro());
			pstm.setTimestamp(6, obj.getFechaActualizacion());
			pstm.setInt(7, obj.getEstado());
			pstm.setInt(8, obj.getCategoria().getIdCategoria());

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

	public List<Libro> listaXTituloIguales(String titulo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Libro> lstSalida = new ArrayList<Libro>();
		try {
			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM libro where titulo = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, titulo);
			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Libro objLibro;
			while (rs.next()) {
				objLibro = new Libro();
				objLibro.setIdLibro(rs.getInt(1));
				objLibro.setTitulo(rs.getString(2));
				lstSalida.add(objLibro);

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

	

	public List<Libro> listaXSerieIguales(String serie) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Libro> lstSalida = new ArrayList<Libro>();

		try {

			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM libro where serie = ? ";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, serie);

			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Libro objLibro;
			while (rs.next()) {
				objLibro = new Libro();
				objLibro.setIdLibro(rs.getInt(1));
				objLibro.setSerie(rs.getString(2));

				lstSalida.add(objLibro);

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
