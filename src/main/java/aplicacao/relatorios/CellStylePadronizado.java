package aplicacao.relatorios;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.FillPatternType;
import static org.apache.poi.ss.usermodel.VerticalAlignment.CENTER;
import static org.apache.poi.ss.usermodel.VerticalAlignment.TOP;

public class CellStylePadronizado {

    private final HSSFWorkbook workbook;

    public static final int TAMANHO_FONTE_CABECALHO = 15;
    public static final int TAMANHO_FONTE_TITULO_COLUNA_CONTEUDO = 10;
    public static final int TAMANHO_FONTE_RODAPE = 8;
    public static final int LARGURA_PADRAO_COLUNAS = 6000;
    public static final String TEXTO_MARCA_GRUPO = "NEOGRID";
    private static final BorderStyle borderStyle = BorderStyle.HAIR;

    @Getter
    private final CellStyle styleConteudoAlinhadoAEsquerda;
    @Getter
    private final CellStyle styleData;
    @Getter
    private final CellStyle styleMarcaGrupo;
    @Getter
    private final CellStyle styleColunaTituloRelatorio;
    @Getter
    private final CellStyle styleTituloRelatorio;


    public CellStylePadronizado(HSSFWorkbook workbook) {
        this.workbook = workbook;
        this.styleConteudoAlinhadoAEsquerda = montaStyleConteudoAlinhadoAEsquerda();     ;
        this.styleData = montaStyleData();
        this.styleMarcaGrupo = montaStyleMarcaGrupo();
        this.styleColunaTituloRelatorio = montaStyleColunaTituloRelatorio();
        this.styleTituloRelatorio = montaStyleTituloRelatorio();
    }

    private CellStyle montaStyleConteudoAlinhadoAEsquerda() {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(Boolean.TRUE);
        style.setVerticalAlignment(TOP);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderTop(borderStyle);
        return style;
    }

    private CellStyle montaStyleData() {
        Font font = workbook.createFont();
        font.setBold(Boolean.TRUE);
        font.setFontHeightInPoints((short)TAMANHO_FONTE_RODAPE);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderTop(borderStyle);
        return style;
    }

    private CellStyle montaStyleMarcaGrupo() {
        Font font = workbook.createFont();
        font.setBold(Boolean.TRUE);
        font.setFontHeightInPoints((short)TAMANHO_FONTE_RODAPE);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderTop(borderStyle);
        return style;
    }

    private CellStyle montaStyleColunaTituloRelatorio() {
        Font font = workbook.createFont();
        font.setBold(Boolean.TRUE);
        font.setFontHeightInPoints((short)TAMANHO_FONTE_TITULO_COLUNA_CONTEUDO);
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(Boolean.TRUE);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setFont(font);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle montaStyleTituloRelatorio() {
        Font font = workbook.createFont();
        font.setBold(Boolean.TRUE);
        font.setFontHeightInPoints((short) TAMANHO_FONTE_CABECALHO);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderTop(borderStyle);
        return style;
    }
}
