package domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitudRecomendacion {
    private Empresa empresa;
    private int puntosMinimos;
    private int donacionesMinimas;
    private int cantidadMaxima;
    List<Colaborador> colaboradores;
}
