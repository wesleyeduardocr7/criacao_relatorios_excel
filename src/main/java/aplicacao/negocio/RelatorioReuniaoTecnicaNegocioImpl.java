package aplicacao.negocio;
import aplicacao.dao.RelatorioReuniaoTecnicaDAOImpl;
import aplicacao.vo.RelatorioReuniaoTecnicaVO;
import java.util.List;

public class RelatorioReuniaoTecnicaNegocioImpl implements RelatorioReuniaoTecnicaNegocio {

    private RelatorioReuniaoTecnicaDAOImpl relatorioReuniaoTecnicaDAO = new RelatorioReuniaoTecnicaDAOImpl();

    @Override
    public List<RelatorioReuniaoTecnicaVO> recuperarPor(Long idObra) throws Exception {
        return relatorioReuniaoTecnicaDAO.recuperarPor(idObra);
    }

    @Override
    public List<RelatorioReuniaoTecnicaVO> recuperarPorObra(Long idObra) throws Exception {
        return relatorioReuniaoTecnicaDAO.recuperarPorObra(idObra);
    }
}
