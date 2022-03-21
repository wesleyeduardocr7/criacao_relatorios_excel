package aplicacao.dominio;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "colaborador")
@Data
@NoArgsConstructor
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_colaborador")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "data_admissao")
    private Date dataAdmissao;

    @Column(name = "salario")
    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @OneToMany(mappedBy = "colaborador",fetch = FetchType.LAZY)
    private List<Tarefa> tarefas;
}
