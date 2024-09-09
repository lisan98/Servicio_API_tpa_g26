package repositories;

import domain.Colaborador;
import main.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ColaboradorRepository {
    public List<Colaborador> obtenerColaboradores(int puntosMinimos, int donacionesMinimas, int cantidadMaxima) {
        List<Colaborador> colaboradores = new ArrayList<>();
        String sql = "SELECT TOP ? id, nombre, puntos, donacionesViandas " +
                "FROM Colaboradores WHERE puntos >= ? AND donacionesViandas >= ? ORDER BY puntos DESC";
        /* aca va una consulta sql que traiga lo necesario de la BBDD principal */

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidadMaxima);
            ps.setInt(2, puntosMinimos);
            ps.setInt(3, donacionesMinimas);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Colaborador colaborador = new Colaborador(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("puntos"),
                            rs.getInt("donacionesViandas")
                    );
                    colaboradores.add(colaborador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colaboradores;
    }
}
