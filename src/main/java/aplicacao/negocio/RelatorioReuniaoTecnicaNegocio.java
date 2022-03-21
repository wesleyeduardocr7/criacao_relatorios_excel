package aplicacao.negocio;
import aplicacao.vo.RelatorioReuniaoTecnicaVO;
import java.util.List;

public interface RelatorioReuniaoTecnicaNegocio {
    List<RelatorioReuniaoTecnicaVO> recuperarPor(Long idObra) throws Exception;
    List<RelatorioReuniaoTecnicaVO> recuperarPorObra(Long idObra) throws Exception;
}
