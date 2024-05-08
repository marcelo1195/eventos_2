package eventos.eventos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EventoDTO {
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 100, message = "O nome não deve exceder 100 caracteres.")
    private String nome;

    @NotBlank(message = "O local não pode estar em branco")
    @Size(max = 100, message = "O nome do local não pode exceder 100 caracteres")
    private String local;

    @NotNull(message = "A data do evento não pode ser nula")
    @Future(message = "A data do evento deve ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataEvento;

    @NotNull(message = "O valor não pode ser nulo.")
    @DecimalMin(value = "0.00", message = "O valor não pode ser negativo")
    private BigDecimal preco;
}
