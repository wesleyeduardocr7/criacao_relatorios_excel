package aplicacao.negocio;
import aplicacao.dao.TarefaDAOImpl;
import aplicacao.dominio.Tarefa;
import java.util.List;

public class TarefaNegocioImpl implements TarefaNegocio {

    private TarefaDAOImpl tarefaDAO = new TarefaDAOImpl();

    @Override
    public List<Tarefa> recuperar() throws Exception {
        return tarefaDAO.recuperar();
    }
}
