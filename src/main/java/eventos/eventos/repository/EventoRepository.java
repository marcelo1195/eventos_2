package eventos.eventos.repository;

import eventos.eventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository <Evento, Long> {
    @Query("SELECT e FROM Evento e WHERE e.dataEVENTO = :data")
    List<Evento> findByDataEvento(@Param("data") LocalDate dataEvento);

    @Query("SELECT e FROM Evento e WHERE e.dataEvento BETWEEN :inicio AND : fim")
    List<Evento> findEventosBetweenDates(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
