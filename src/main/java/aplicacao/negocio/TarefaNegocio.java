package aplicacao.negocio;
import aplicacao.dominio.Tarefa;
import java.util.List;

public interface TarefaNegocio {
    List<Tarefa> recuperar() throws Exception;
}
