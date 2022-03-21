package aplicacao.dao;
import aplicacao.dao.generico.GenericDAO;
import aplicacao.dominio.Tarefa;
import java.util.List;

public interface TarefaDAO extends GenericDAO<Tarefa, Long> {
    List<Tarefa> recuperar() throws Exception;
}
