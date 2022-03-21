package aplicacao.dao.generico;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T, K extends Serializable> {

    void salvar(T obj) throws Exception;

    List<T> recuperar() throws Exception;

    Connection getConnection();
}
