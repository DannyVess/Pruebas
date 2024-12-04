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
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;	
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into libro (idLibro,titulo, anio, serie, tema, "
            		+ " fechaRegistro, fechaActualizacion,estado, idCategoria ) "
            		+ " values(null,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnio());
			pstm.setString(3, obj.getSerie());			
			pstm.setString(4, obj.getTema());		
			pstm.setTimestamp(5, obj.getFechaRegistro());
			pstm.setTimestamp(6, obj.getFechaActualizacion());
			pstm.setInt(7, obj.getEstado());
			pstm.setInt(8, obj.getCategoria().getIdCategoria());
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

	public boolean existeTitulo(String titulo) {
		boolean existe = false;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "select * from libro where titulo = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, titulo);
			rs = pstm.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return existe;
	}
	
	public boolean existeSerie(String serie) {
		boolean existe = false;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "select * from libro where serie = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, serie);
			rs = pstm.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return existe;
	}
	
	
	public boolean existeAnio(int anio) {
		boolean existe = false;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "select * from libro where anio = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, anio);   //setString
			rs = pstm.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return existe;
	}
	
	
	
}
