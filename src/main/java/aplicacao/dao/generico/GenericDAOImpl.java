package aplicacao.dao.generico;
import org.hibernate.HibernateException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class GenericDAOImpl<T, K extends Serializable> implements GenericDAO<T, K>, Serializable {

    protected static final String ERRO_ACESSO_A_BASE_DADOS = "Ocorreu um erro ao "
            + "acessar a base de dados.";

    protected Class<T> claz;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("relatorios_excel");
    private EntityManager em = emf.createEntityManager();

    public GenericDAOImpl() {
        this.claz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public EntityManager getEntity() {
        return em;
    }

    @Override
    public void salvar(T obj) throws Exception {
        try {
            iniciaTransacao();
            em.persist(obj);
            confirmaTransacao();
        } catch (HibernateException e) {
            throw new Exception(ERRO_ACESSO_A_BASE_DADOS, e);
        }
    }

    @Override
    public List<T> recuperar() throws Exception {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(claz));
            return em.createQuery(cq).getResultList();
        } catch (HibernateException e) {
            throw new Exception(ERRO_ACESSO_A_BASE_DADOS, e);
        }
    }

    @Override
    public Connection getConnection(){
        String DB_URL = "jdbc:mysql://localhost/relatorios_excel?serverTimezone=UTC";
        final String USER = "root";
        final String PASS = "root";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void iniciaTransacao() {
        em.getTransaction().begin();
    }

    private void confirmaTransacao() {
        em.getTransaction().commit();
    }
}
