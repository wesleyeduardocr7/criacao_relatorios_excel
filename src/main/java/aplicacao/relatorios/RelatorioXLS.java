package aplicacao.relatorios;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RelatorioXLS {

    private final HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private final CellStylePadronizado cellStylePadronizado;
    private int contadorLinha = 0;
    @Setter
    private int quantidaDeColunasDoRelatorio;

    public RelatorioXLS() {
        workbook = new HSSFWorkbook();
        cellStylePadronizado = new CellStylePadronizado(workbook);
    }

    public void inicializaFolha(String nomeDaFolha) {
        sheet = workbook.createSheet(nomeDaFolha);
    }

    public void adicionarLogoTipo(byte[] logo, double larguraLogo, double alturaLogo, String areaMergeLogo) {
        adicionarMergeEmAreaEspecifica(areaMergeLogo);
        if (ArrayUtils.isNotEmpty(logo)){
            int index = workbook.addPicture(logo, Workbook.PICTURE_TYPE_PNG);
            CreationHelper helper = workbook.getCreationHelper();
            Drawing<HSSFShape> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = drawing.createPicture(anchor, index);
            pict.resize(larguraLogo,alturaLogo);
        }
    }

    public void adicionarCabecalho(String nomeCabecalho, String areaMergeCabecalho) {
        adicionarMergeEmAreaEspecifica(areaMergeCabecalho);
        adicionaTituloCabecalho(nomeCabecalho);
    }

    public void adicionarMergeEmAreaEspecifica(String areaDoMerge){
        sheet.addMergedRegion(CellRangeAddress.valueOf(areaDoMerge));
    }

    public void adicionaTituloCabecalho(String nomeTitulo){
        Row primeiraLinha = sheet.createRow(0);
        Cell segundaCelulaDaLinha = primeiraLinha.createCell(1);
        segundaCelulaDaLinha.setCellValue(nomeTitulo);
        segundaCelulaDaLinha.setCellStyle(cellStylePadronizado.getStyleTituloRelatorio());
    }

    public void preencherLinhaDeTitulosConteudoRelatorio(List<String> nomeDasColunas) {
        Row row = criaNovaLinhaEmUmaPosicao(6);
        adicionaMergeNaColunaDeTituloDeConteudo(row);
        AtomicInteger contador = new AtomicInteger();
        List<String> enderecosDasCelulas = new ArrayList<>();
        nomeDasColunas.forEach(nomeDaColuna -> {
            Cell cellColuna = row.createCell(contador.get());
            cellColuna.setCellValue(nomeDaColuna);
            sheet.setColumnWidth(contador.get(), CellStylePadronizado.LARGURA_PADRAO_COLUNAS);
            cellColuna.setCellStyle(cellStylePadronizado.getStyleColunaTituloRelatorio());
            enderecosDasCelulas.add(String.valueOf((cellColuna.getAddress())));
            contador.getAndIncrement();
        });
        adicionaFiltrosNasColunas(enderecosDasCelulas);
    }

    private void adicionaFiltrosNasColunas(List<String> enderecosDasCelulas){
        String enderecoPrimeiraColuna = enderecosDasCelulas.get(0);
        String enderecoUltimaColuna = enderecosDasCelulas.get(enderecosDasCelulas.size()-1);
        sheet.setAutoFilter(CellRangeAddress.valueOf(enderecoPrimeiraColuna+":"+enderecoUltimaColuna));
    }

    private void adicionaMergeNaColunaDeTituloDeConteudo(Row row){
        int firstRow = row.getRowNum();
        int lastRow = row.getRowNum()+1;
        int firstCol;
        int lastCol;
        for(int i = 0; i < quantidaDeColunasDoRelatorio+1; i++){
            firstCol = i;
            lastCol = i;
            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
        }
    }

    public void adicionarLinhaDeEspaco(int posicaoLinha) {
        Row row = criaNovaLinhaEmUmaPosicao(posicaoLinha);
        row.setHeight( (short) 70);
        criaMergeNaLinhaOndeFoiInseridoUmEspaco(row);
    }

    private void criaMergeNaLinhaOndeFoiInseridoUmEspaco(Row row){
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, quantidaDeColunasDoRelatorio));
    }

    public void adicionarConteudoEmUmaLinha(List<String> valoresDasCelulas) {
        Row row = criaNovaLinha();
        row.setHeight((short) 300);
        AtomicInteger contador = new AtomicInteger();
        for (String valorCelula : valoresDasCelulas) {
            Cell cellConteudo = row.createCell(contador.get());
            cellConteudo.setCellValue(valorCelula);
            cellConteudo.setCellStyle(cellStylePadronizado.getStyleConteudoAlinhadoAEsquerda());
            contador.getAndIncrement();
        }
    }

    public void aplicarConfiguracaoDeImpressao(boolean impressaoEmPaisagem, short scalaDaAreaDeImpressao) {
        int sheetIndex = 0;
        int startColumn = 0;
        int endColumn = quantidaDeColunasDoRelatorio;
        int startRow = 0;
        int endRow = sheet.getLastRowNum();
        sheet.getPrintSetup().setLandscape(impressaoEmPaisagem);
        sheet.getPrintSetup().setScale(scalaDaAreaDeImpressao);
        workbook.setPrintArea(sheetIndex,startColumn,endColumn,startRow,endRow);
        sheet.setDisplayGridlines (Boolean.TRUE);
        sheet.getPrintSetup().setPaperSize (XSSFPrintSetup.A4_PAPERSIZE);
        sheet.setPrintGridlines (Boolean.TRUE);
    }

    public void adicionarLinhaDeRodape() {
        Row row = sheet.getRow(sheet.getLastRowNum());
        row.setHeight((short) 300);
        adicionaCelulaData(row);
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 1, quantidaDeColunasDoRelatorio));
        adicionaCelulaMarcaGrupo(row);
    }

    private void adicionaCelulaData(Row row) {
        Cell cellData = row.createCell(0);
        cellData.setCellValue(new Date().toString());
        cellData.setCellStyle(cellStylePadronizado.getStyleData());
    }

    private void adicionaCelulaMarcaGrupo(Row row) {
        for(int i = 1 ; i < quantidaDeColunasDoRelatorio; i++){
            row.createCell(i).setCellStyle(cellStylePadronizado.getStyleMarcaGrupo());
        }
        Cell cellMarcaDoGrupo = row.getCell(1);
        cellMarcaDoGrupo.setCellValue(CellStylePadronizado.TEXTO_MARCA_GRUPO);
    }

    public Row criaNovaLinha() {
        Row row = sheet.createRow(this.contadorLinha);
        this.contadorLinha++;
        return row;
    }

    public Row criaNovaLinhaEmUmaPosicao(int linha) {
        this.contadorLinha = linha;
        Row row = sheet.createRow(this.contadorLinha);
        this.contadorLinha++;
        return row;
    }

    public void geraArquivoDeSaida(String nomeArquivo) {
        String nomeDoArquivoDeSaida = formataNomeDoArquivoDeSaida(nomeArquivo);
        FileOutputStream out;
        try {
            out = new FileOutputStream(nomeDoArquivoDeSaida);
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formataNomeDoArquivoDeSaida(String nomeArquivo) {
        return nomeArquivo
                .concat("-")
                .concat(String.valueOf(LocalDateTime.now().getNano()))
                .concat(".xls");
    }
}
