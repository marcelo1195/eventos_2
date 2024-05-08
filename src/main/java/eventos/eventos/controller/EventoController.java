package eventos.eventos.controller;

import eventos.eventos.dto.EventoDTO;
import eventos.eventos.model.Evento;
import eventos.eventos.service.EventoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    //busca em data
    @GetMapping("/por-data")
    public ResponseEntity<List<Evento>> getEventoPorData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Evento> eventos = eventoService.findByDataEvento(date);
        if (eventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventos);
    }

    // Busca eventos entre dadas
    @GetMapping("/entre-datas")
    public ResponseEntity<List<Evento>> getEventosEntreDatas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
            List<Evento> eventos = eventoService.findEventoBetweenDates(inicio, fim);
                if (eventos.isEmpty()){
                    return ResponseEntity.noContent().build();
                }
                return ResponseEntity.ok(eventos);
    }
    @PostMapping
    public ResponseEntity<?> createEvento(@RequestBody @Valid EventoDTO eventoDTO) {
        Evento evento = convertToEntity(eventoDTO);
        Evento novoEvento = eventoService.save(evento);
        return ResponseEntity.ok(novoEvento);
    }

    // Método auxiliar para converter DTO para entidade
    private Evento convertToEntity(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setNome(eventoDTO.getNome());
        evento.setLocal(eventoDTO.getLocal());
        evento.setDataEvento(eventoDTO.getDataEvento());
        evento.setPreco(eventoDTO.getPreco());
        return evento;
    }

    // Buscar todos os eventos
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos(){
        List<Evento> eventos = eventoService.findAll();
        if (eventos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventos);
    }

    //Endpoint para deletar um evento por ID
    @DeleteMapping
    public ResponseEntity<?> deleteEvento(@PathVariable Long id) {
        if (!eventoService.findById(id).isPresent()){
            return  ResponseEntity.notFound().build();
        }
        eventoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
