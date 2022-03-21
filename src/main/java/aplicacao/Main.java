package aplicacao;
import aplicacao.negocio.*;
import aplicacao.relatorios.RelatorioReuniaoTecnica;
import aplicacao.vo.RelatorioReuniaoTecnicaVO;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static RelatorioReuniaoTecnicaNegocioImpl relatorioReuniaoTecnicaNegocio = new RelatorioReuniaoTecnicaNegocioImpl();

    public static void main(String[] args) throws Exception {

        //List<RelatorioReuniaoTecnicaVO> relatorioReuniaoTecnicaVOS = relatorioReuniaoTecnicaNegocio.recuperarPorObra(null);
        List<RelatorioReuniaoTecnicaVO> relatorioReuniaoTecnicaVOS = relatorioReuniaoTecnicaNegocio.recuperarPor(1L);

        relatorioReuniaoTecnicaVOS = relatorioReuniaoTecnicaVOS.stream().limit(27).collect(Collectors.toList());

        new RelatorioReuniaoTecnica().geraArquivoXls(relatorioReuniaoTecnicaVOS);
    }
}


