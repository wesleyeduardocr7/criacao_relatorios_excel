package aplicacao.dao;
import aplicacao.dao.generico.GenericDAO;
import aplicacao.dominio.Obra;
import aplicacao.vo.RelatorioReuniaoTecnicaVO;
import java.util.List;

public interface RelatorioReuniaoTecnicaDAO extends GenericDAO<Obra, Long> {
    List<RelatorioReuniaoTecnicaVO> recuperarPor(Long idObra) throws Exception;
}
