package main;

import domain.Colaborador;
import io.javalin.Javalin;
import repositories.ColaboradorRepository;

import java.util.List;

public class main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        ColaboradorRepository repo = new ColaboradorRepository();

        app.get("/colaboradores", ctx -> {
            int puntosMinimos = Integer.parseInt(ctx.queryParam("puntosMinimos"));
            int donacionesMinimas = Integer.parseInt(ctx.queryParam("donacionesMinimas"));
            int cantidadMaxima = Integer.parseInt(ctx.queryParam("cantidadMaxima"));

            List<Colaborador> colaboradores = repo.obtenerColaboradores(puntosMinimos, donacionesMinimas, cantidadMaxima);
            /*tengo que agregar un repository para crear las clases para luego persistir*/
            ctx.json(colaboradores);
        });
    }
}
