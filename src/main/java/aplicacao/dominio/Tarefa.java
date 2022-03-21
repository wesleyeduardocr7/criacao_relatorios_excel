package aplicacao.dominio;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tarefa")
@Data
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tarefa")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "unidade_medida")
    private String unidade_medida;

    @Column(name = "preco")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    private Colaborador colaborador;
}
