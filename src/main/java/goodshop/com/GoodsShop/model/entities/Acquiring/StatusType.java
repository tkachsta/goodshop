package goodshop.com.GoodsShop.model.entities.Acquiring;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acquiring_status")
public class StatusType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String code;
    @Column
    private String name;

}
