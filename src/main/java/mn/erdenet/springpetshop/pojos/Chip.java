package mn.erdenet.springpetshop.pojos;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String chipId;
    private String type;
    @OneToOne(mappedBy = "chip")
    @ToString.Exclude
    private Pet pet;

}
