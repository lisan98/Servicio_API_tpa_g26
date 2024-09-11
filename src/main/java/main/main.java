package main;

import domain.Colaborador;
import domain.SolicitudRecomendacion;
import io.javalin.Javalin;
import repositories.ColaboradorRepository;
import repositories.SolicitudRepository;

import java.util.List;

public class main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        SolicitudRepository solirepo = new SolicitudRepository();
        ColaboradorRepository colarepo;

        app.get("/colaboradores", ctx -> {
            int puntosMinimos = Integer.parseInt(ctx.queryParam("puntosMinimos"));
            int donacionesMinimas = Integer.parseInt(ctx.queryParam("donacionesMinimas"));
            int cantidadMaxima = Integer.parseInt(ctx.queryParam("cantidadMaxima"));

            SolicitudRecomendacion solicitud = solirepo.crearSolicitud(puntosMinimos, donacionesMinimas, cantidadMaxima);
            solicitud.setColaboradores( solirepo.obtenerColaboradores(puntosMinimos, donacionesMinimas, cantidadMaxima));
            List<Colaborador> colaboradores = solicitud.getColaboradores();
            /*tengo que agregar un repository para crear las clases para luego persistir*/
            ctx.json(colaboradores);
        });
    }
}
