package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Tesis;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class ModelTesis {

    // Método para insertar una tesis
    public int insertarTesis(Tesis obj) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int salida = -1;
        try {
            // 1. Conexión
            conn = MySqlDBConexion.getConexion();

            // 2. Preparar SQL
            String sql = "INSERT INTO tesis (titulo, tema, fechaCreacion, fechaRegistro, fechaActualizacion, estado, idAlumno) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, obj.getTitulo());
            pstm.setString(2, obj.getTema());
            pstm.setDate(3, obj.getFechaCreacion());
            pstm.setTimestamp(4, obj.getFechaRegistro());
            pstm.setTimestamp(5, obj.getFechaActualizacion());
            pstm.setInt(6, obj.getEstado());
            pstm.setInt(7, obj.getAlumno().getIdAlumno());

            log.info("SQL => " + pstm);

            // 3. Ejecutar SQL
            salida = pstm.executeUpdate();

        } catch (Exception e) {
            log.error("Error en insertarTesis", e);
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {}
        }
        return salida;
    }
}