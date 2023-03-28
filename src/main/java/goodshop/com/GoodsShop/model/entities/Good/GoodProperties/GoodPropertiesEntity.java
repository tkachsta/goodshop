package goodshop.com.GoodsShop.model.entities.Good.GoodProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class GoodPropertiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type")
    private String goodType;
    @Column
    private String weight;
    @Column
    private Integer height;
    @Column(name = "fragile")
    private boolean isFragile;

}
