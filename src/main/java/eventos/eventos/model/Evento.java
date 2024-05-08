package eventos.eventos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "eventos")
@Data
public class Evento {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 100, message = "O nome não deve exeder 100 caracteres.")
    private String nome;

    @Column(name = "local")
    @NotBlank(message = "O local não pode estar em branco")
    @Size(max = 100, message = "O nome do local não pode exeder 100 caracteres")
    private String local;

    @Column(name = "dataEvento")
    @NotNull(message = "A data do evento não pode ser nula")
    @Future(message = "A data do evento deve ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataEvento;

    @Column(name = "preco")
    @NotNull(message = "O valor não pode ser nulo.")
    @DecimalMin(value = "0.00", message = "O valor não pode ser negativo")
    private BigDecimal preco;
}
