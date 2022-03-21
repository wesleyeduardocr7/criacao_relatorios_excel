package aplicacao.vo;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class RelatorioReuniaoTecnicaVO {

    private String obra;
    private String colaborador;
    private Date admissao;
    private String matricula;
    private BigDecimal salario;
    private String tarefa;
    private BigDecimal preco;
}
