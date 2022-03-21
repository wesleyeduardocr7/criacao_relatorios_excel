package aplicacao.negocio;
import aplicacao.dominio.Obra;
import java.util.List;

public interface ObraNegocio {
    List<Obra> recuperar() throws Exception;

    Obra recuperarPor(Long idObra) throws Exception;
}
