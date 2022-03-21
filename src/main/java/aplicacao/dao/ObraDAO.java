package aplicacao.dao;
import aplicacao.dao.generico.GenericDAO;
import aplicacao.dominio.Obra;
import java.util.List;

public interface ObraDAO extends GenericDAO<Obra, Long> {

    List<Obra> recuperar() throws Exception;

    Obra recuperarPor(Long idObra) throws Exception;
}

