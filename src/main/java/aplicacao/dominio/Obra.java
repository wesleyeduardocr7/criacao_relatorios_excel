package aplicacao.dominio;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "obra")
@Data
@NoArgsConstructor
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_obra")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "encarregado")
    private String encarregado;

    @OneToMany(mappedBy = "obra", fetch = FetchType.LAZY)
    private List<Colaborador> colaboradors;
}
