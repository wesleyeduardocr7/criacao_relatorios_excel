package aplicacao.negocio;
import aplicacao.dao.ObraDAOImpl;
import aplicacao.dominio.Obra;
import java.util.List;

public class ObraNegocioImpl implements ObraNegocio {

    private ObraDAOImpl obraDAO = new ObraDAOImpl();

    @Override
    public List<Obra> recuperar() throws Exception {
        return obraDAO.recuperar();
    }

    @Override
    public Obra recuperarPor(Long idObra) throws Exception {
        return obraDAO.recuperarPor(idObra);
    }
}
