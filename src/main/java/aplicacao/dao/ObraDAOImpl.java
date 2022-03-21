package aplicacao.dao;
import aplicacao.dao.generico.GenericDAOImpl;
import aplicacao.dominio.Obra;
import javax.persistence.Query;
import java.util.List;

public class ObraDAOImpl extends GenericDAOImpl<Obra, Long> implements ObraDAO {

    @Override
    public Obra recuperarPor(Long idObra) throws Exception {

        String hql = "FROM Obra o WHERE o.id = :idObra";

        Query query = getEntity().createQuery(hql);

        query.setParameter("idObra", idObra);

        List<Obra> obras = query.getResultList();

        return obras.get(0);
    }
}
