package repositories;

import domain.Colaborador;
import domain.SolicitudRecomendacion;
import main.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRepository {

    private static final List<SolicitudRecomendacion> solicitudList = new ArrayList<>();

    public SolicitudRecomendacion crearSolicitud(int puntosMinimos, int donacionesMinimas, int cantidadMaxima){
        SolicitudRecomendacion solicitud = new SolicitudRecomendacion();
        solicitud.setPuntosMinimos(puntosMinimos);
        solicitud.setDonacionesMinimas(donacionesMinimas);
        solicitud.setCantidadMaxima(cantidadMaxima);
        return solicitud;
    }
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
                    if (ColaboradorRepository.getbyId(rs.getInt("id"))==null) {
                        Colaborador colaborador = new Colaborador();
                        colaborador.setId(rs.getInt("id"));
                        colaborador.setNombre(rs.getString("nombre"));
                        colaborador.setPuntaje(rs.getInt("puntos"));
                        colaborador.setColabRealizadas(rs.getInt("donacionesViandas"));
                        colaboradores.add(colaborador);
                    }else{
                        colaboradores.add(ColaboradorRepository.getbyId(rs.getInt("id")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colaboradores;
    }
}
