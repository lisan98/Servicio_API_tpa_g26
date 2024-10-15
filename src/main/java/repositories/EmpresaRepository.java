package repositories;

import domain.Colaborador;
import domain.Empresa;

import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository {
    private static final List<Empresa> empresasList = new ArrayList<>();

    public  Empresa crearEmpresa( String empresa){
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNombre(empresa);
        empresasList.add(nuevaEmpresa);
        return nuevaEmpresa;
    }
}
