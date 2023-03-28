package goodshop.com.GoodsShop.model.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {

    private Integer id;
    private String stockName;
    private String city;
    private Integer amount;


}
