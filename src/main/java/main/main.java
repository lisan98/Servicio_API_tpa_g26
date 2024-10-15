package main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Colaborador;
import domain.Empresa;
import domain.SolicitudRecomendacion;
import io.javalin.Javalin;
import repositories.ColaboradorRepository;
import repositories.EmpresaRepository;
import repositories.SolicitudRepository;


import java.util.List;

public class main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        SolicitudRepository solirepo = new SolicitudRepository();
        ColaboradorRepository colarepo = new ColaboradorRepository();
        EmpresaRepository emprerepo = new EmpresaRepository();

        app.get("api/recomendar", ctx -> {
            String nombreEmpresa = (ctx.body());
            int puntosMinimos = Integer.parseInt(ctx.body());
            int donacionesMinimas = Integer.parseInt(ctx.body());
            int maxColaboradores = Integer.parseInt(ctx.body());

            Empresa nuevaEmpresa = emprerepo.crearEmpresa(nombreEmpresa);
            SolicitudRecomendacion solicitud = solirepo.crearSolicitud(puntosMinimos, donacionesMinimas, maxColaboradores);
            List<Colaborador> colaboradores =solirepo.obtenerColaboradores(puntosMinimos, donacionesMinimas, maxColaboradores);
            solicitud.setColaboradores(colaboradores);
            solicitud.setEmpresa(nuevaEmpresa);

            ctx.json(colaboradores);
        });

        app.post("/api/colaboradores", ctx -> {
            List<Colaborador> colaboradores = ObjectMapper.readValue(
                    ctx.body(),
                    new TypeReference<List<Colaborador>>() {}
            );

            // Guardar colaboradores
            colarepo.guardarColaboradores(colaboradores);

            ctx.status(201).result("Colaboradores almacenados correctamente.");
        });
    }
}
