package eventos.eventos.service;

import eventos.eventos.model.Evento;
import eventos.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findAll(){
        return eventoRepository.findAll();
    }

    public Optional<Evento> findById(Long id){
        return eventoRepository.findById(id);
    }

    public Evento save(Evento evento){
        return eventoRepository.save(evento);
    }

    public void deleteById(Long id){
        eventoRepository.deleteById(id);
    }

    public List<Evento> findByDataEvento(LocalDate date){
        return eventoRepository.findByDataEvento(date);
    }

    public List<Evento> findEventoBetweenDates(LocalDate inicio, LocalDate fim){
        return eventoRepository.findEventosBetweenDates(inicio, fim);
    }
}
