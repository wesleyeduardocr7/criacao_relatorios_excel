package aplicacao.dao;
import aplicacao.dao.generico.GenericDAOImpl;
import aplicacao.dominio.Obra;
import aplicacao.utils.QueryUtils;
import aplicacao.vo.RelatorioReuniaoTecnicaVO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RelatorioReuniaoTecnicaDAOImpl extends GenericDAOImpl<Obra, Long> implements RelatorioReuniaoTecnicaDAO {

    @Override
    public List<RelatorioReuniaoTecnicaVO> recuperarPor(Long idObra) throws Exception {
        String sql = QueryUtils.getQuery("query_relatorio.txt");
        return new QueryRunner().query(getConnection(),sql,new BeanListHandler<>(RelatorioReuniaoTecnicaVO.class),idObra);
    }

    public List<RelatorioReuniaoTecnicaVO> recuperarPorObra(Long idObra) {

        StringBuilder sql = new StringBuilder();

        sql.append( "select o.nome as obra,\n" +
                "       c.nome as colaborador,\n" +
                "       c.data_admissao as admissao,\n" +
                "       c.matricula,\n" +
                "       c.salario,\n" +
                "       t.descricao as tarefa,\n" +
                "       t.preco\n" +
                "from obra o\n" +
                "         join colaborador c on o.id_obra = c.id_obra\n" +
                "         join tarefa t on c.id_colaborador = t.id_colaborador\n" );

        if(Objects.nonNull(idObra)){
            sql.append( "where o.id_obra = :id_obra" );
        }

        Query query = getEntity().createNativeQuery(sql.toString());

        if(Objects.nonNull(idObra)){
            query.setParameter("id_obra",idObra);
        }

        List<Object[]> resultado = query.getResultList();

        List<RelatorioReuniaoTecnicaVO> relatorioReuniaoTecnicaVOS = new ArrayList<>();
        for(Object[] r : resultado){
            RelatorioReuniaoTecnicaVO relatorioReuniaoTecnicaVO = new RelatorioReuniaoTecnicaVO();
            relatorioReuniaoTecnicaVO.setObra((String) r[0]);
            relatorioReuniaoTecnicaVO.setColaborador((String) r[1]);
            relatorioReuniaoTecnicaVO.setAdmissao((Date) r[2]);
            relatorioReuniaoTecnicaVO.setMatricula((String) r[3]);
            relatorioReuniaoTecnicaVO.setSalario((BigDecimal) r[4]);
            relatorioReuniaoTecnicaVO.setTarefa((String) r[5]);
            relatorioReuniaoTecnicaVO.setPreco((BigDecimal) r[6]);
            relatorioReuniaoTecnicaVOS.add(relatorioReuniaoTecnicaVO);
        }
        return relatorioReuniaoTecnicaVOS;
    }
}
