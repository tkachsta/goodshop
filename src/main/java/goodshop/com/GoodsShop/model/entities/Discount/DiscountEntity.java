package goodshop.com.GoodsShop.model.entities.Discount;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Table
@Entity(name = "discount")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer discount;
    @Column(name = "publish_date")
    private LocalDateTime publishDate;
    @Column(name = "expire_date")
    private LocalDateTime expireDate;
    @OneToMany(mappedBy = "discount")
    private Set<GoodEntity> goodEntities;

}
