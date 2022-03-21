package aplicacao.negocio;
import aplicacao.dao.ColaboradorDAOImpl;
import aplicacao.dominio.Colaborador;
import java.util.List;

public class ColaboradorNegocioImpl implements ColaboradorNegocio {

    private ColaboradorDAOImpl colaboradorDAO = new ColaboradorDAOImpl();

    @Override
    public List<Colaborador> recuperar() throws Exception {
        return colaboradorDAO.recuperar();
    }
}
