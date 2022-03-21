package aplicacao.relatorios;
import aplicacao.vo.RelatorioReuniaoTecnicaVO;
import java.util.ArrayList;
import java.util.List;

public class RelatorioReuniaoTecnica {

    private static final String NOME_DO_ARQUIVO_DE_SAIDA = "reuniaoTecnica";
    private static final String NOME_DA_FOLHA = "Reunião Técnica";
    private static final String TITULO_CABECALHO = " Relatório Reunião Técnica";
    private static final String AREA_MERGE_LOGO = "A1:A5";
    private static final String AREA_MERGE_CABECALHO = "B1:G5";
    private static final int QUANTIDADE_COLUNAS_RELATORIO = 6;
    private static final double ALTURA_LOGO = 5;
    private static final double LARGURA_LOGO = 1;
    private static final boolean IS_PAISAGEM = true;
    private static final short SCALA_AREA_IMPRESSAO = 80;

    public void geraArquivoXls(List<RelatorioReuniaoTecnicaVO> relatorioReuniaoTecnicaVOS){

       RelatorioXLS relatorioXLS = new RelatorioXLS();

       relatorioXLS.setQuantidaDeColunasDoRelatorio(QUANTIDADE_COLUNAS_RELATORIO);

       relatorioXLS.inicializaFolha(NOME_DA_FOLHA);

       relatorioXLS.adicionarLogoTipo(new byte[0],ALTURA_LOGO,LARGURA_LOGO,AREA_MERGE_LOGO);

       relatorioXLS.adicionarCabecalho(TITULO_CABECALHO, AREA_MERGE_CABECALHO);

       relatorioXLS.adicionarLinhaDeEspaco(5);

       relatorioXLS.preencherLinhaDeTitulosConteudoRelatorio(retornaNomeDasColunasDeTituloDeConteudo());

       preencheConteudoDoRelatorio(relatorioXLS,relatorioReuniaoTecnicaVOS);

       relatorioXLS.aplicarConfiguracaoDeImpressao(IS_PAISAGEM,SCALA_AREA_IMPRESSAO);

       relatorioXLS.adicionarLinhaDeRodape();

       relatorioXLS.geraArquivoDeSaida(NOME_DO_ARQUIVO_DE_SAIDA);
    }


    private List<String> retornaNomeDasColunasDeTituloDeConteudo(){
        List<String> nomeDasColunas = new ArrayList<>();
        nomeDasColunas.add("OBRA");
        nomeDasColunas.add("COLABORADOR");
        nomeDasColunas.add("ADMISSÃO");
        nomeDasColunas.add("MATRICULA");
        nomeDasColunas.add("SALARIO");
        nomeDasColunas.add("TAREFA");
        nomeDasColunas.add("PREÇO");
        return nomeDasColunas;
    }

    private void preencheConteudoDoRelatorio(RelatorioXLS relatorioXLS, List<RelatorioReuniaoTecnicaVO> relatorioReuniaoTecnicaVOS) {
        relatorioReuniaoTecnicaVOS.forEach(item -> relatorioXLS.adicionarConteudoEmUmaLinha(retornaLinhaDeConteudo(item)));
    }

    private List<String> retornaLinhaDeConteudo(RelatorioReuniaoTecnicaVO item){
        List<String> valoresDosAtributos = new ArrayList<>();
        valoresDosAtributos.add(item.getObra());
        valoresDosAtributos.add(item.getColaborador());
        valoresDosAtributos.add(item.getAdmissao().toString());
        valoresDosAtributos.add(item.getMatricula());
        valoresDosAtributos.add(item.getSalario().toString());
        valoresDosAtributos.add(item.getMatricula());
        valoresDosAtributos.add(item.getPreco().toString());
        return valoresDosAtributos;
    }
}
