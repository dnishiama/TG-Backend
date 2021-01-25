package univap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import univap.model.Historico;

public interface HistoricoRepo extends JpaRepository<Historico, Long>{
	public Historico findByPatrimonioAndMesAndAno(Long patrimonio, Long mes, Long ano);
	
	List<Historico> findByMesAndAno(Long mes, Long ano);
	
	@Query("select h.impressora.departamento.ccusto as ccusto, sum(h.contadorMono) as contadorMono, sum(h.contadorColor) as contadorColor "
			+ "from Historico as h "
			+ "where h.mes = :mes and h.ano = :ano "
			+ "group by ccusto")
	public List<Object[]> rateio(@Param("mes") Long mes, @Param("ano") Long ano);

}
