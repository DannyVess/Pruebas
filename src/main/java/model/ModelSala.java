package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Sala;
import util.MySqlDBConexion;

public class ModelSala {
	
	public int insertaSala(Sala obj) {
		int salida = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "insert into sala (idSala,numero, piso, numAlumnos, recursos, "
            		+ " fechaRegistro,estado, fechaActualizacion, idSede ) "
            		+ " values(null,?,?,?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setTimestamp(5, obj.getFechaRegistro());
			pstm.setInt(6, obj.getEstado());
			pstm.setTimestamp(7, obj.getFechaActualizacion());
			pstm.setInt(8, obj.getSede().getIdSede());
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return salida;
	}
}
