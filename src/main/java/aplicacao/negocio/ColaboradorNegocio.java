package aplicacao.negocio;
import aplicacao.dominio.Colaborador;
import java.util.List;

public interface ColaboradorNegocio {
    List<Colaborador> recuperar() throws Exception;
}
