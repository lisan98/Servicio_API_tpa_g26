package domain;

import java.util.List;

public class SolicitudRecomendacion {
    private Empresa empresa;
    private List<Requisito> requisitos;

    public void agregarRequisito(Requisito requisito){
        requisitos.add(requisito);
    }
}
