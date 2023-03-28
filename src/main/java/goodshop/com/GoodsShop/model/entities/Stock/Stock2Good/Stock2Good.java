package goodshop.com.GoodsShop.model.entities.Stock.Stock2Good;

import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.Stock.StockEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock2good")
public class Stock2Good {
    @EmbeddedId
    private Stock2GoodKey companyKey;
    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "id_stock")
    private StockEntity stock;
    @ManyToOne
    @MapsId("goodId")
    @JoinColumn(name = "id_good")
    private GoodEntity good;
    @Column
    private Integer value;

}
