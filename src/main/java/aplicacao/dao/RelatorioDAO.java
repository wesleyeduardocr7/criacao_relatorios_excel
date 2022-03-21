package aplicacao.dao;
import aplicacao.dao.generico.GenericDAO;
import aplicacao.dominio.Colaborador;
import java.util.List;

public interface RelatorioDAO extends GenericDAO<Colaborador, Long> {
    List<Colaborador> recuperar() throws Exception;
}
