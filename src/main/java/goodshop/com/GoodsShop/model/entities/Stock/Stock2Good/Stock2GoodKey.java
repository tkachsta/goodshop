package goodshop.com.GoodsShop.model.entities.Stock.Stock2Good;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.Stock.StockEntity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Stock2GoodKey implements Serializable {
    @Column(name = "id_stock")
    private Integer stockId;
    @Column(name = "id_good")
    private Integer goodId;


}
