package repositories;

import domain.Colaborador;
import main.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ColaboradorRepository {
    private static final List<Colaborador> colaboradoresList = new ArrayList<>();
    public boolean addColaborador(Colaborador colaborador){
        return colaboradoresList.add(colaborador);
    }

    public static Colaborador getbyId(int id){
        return colaboradoresList.stream()
                .filter(t -> t.getId()== id)
                .findFirst()
                .orElse(null);
    }

    public boolean guardarColaboradores(List<Colaborador> colaboradores){
        colaboradores.forEach(this::addColaborador);
        return true;
    }


    public boolean eliminarColaborador(int id){
        return colaboradoresList.removeIf(t->t.getId()== id);
    }




}


