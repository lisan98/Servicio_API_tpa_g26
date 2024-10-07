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
        ColaboradorRepository colarepo = new ColaboradorRepository();

        app.get("api/colaboradores", ctx -> {
            int puntosMinimos = Integer.parseInt(ctx.body());
            int donacionesMinimas = Integer.parseInt(ctx.body());
            int maxColaboradores = Integer.parseInt(ctx.body());

            SolicitudRecomendacion solicitud = solirepo.crearSolicitud(puntosMinimos, donacionesMinimas, maxColaboradores);
            List<Colaborador> colaboradores =solirepo.obtenerColaboradores(puntosMinimos, donacionesMinimas, maxColaboradores);
            solicitud.setColaboradores(colaboradores);
            colarepo.addColaborador(colaborador);
            /*tengo que agregar un metodo para agregar una lista al repo*/
            ctx.json(colaboradores);
        });
    }
}
