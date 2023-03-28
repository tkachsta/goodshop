package goodshop.com.GoodsShop.model.entities.Stock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stocks")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "stock_name")
    private String stockName;
    private String city;
    private String address;
    private String phone;



}
