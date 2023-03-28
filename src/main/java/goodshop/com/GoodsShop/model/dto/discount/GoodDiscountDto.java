package goodshop.com.GoodsShop.model.dto.discount;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoodDiscountDto {
    private Integer id;
    private String name;
    private List<StockGoodsDto> stocks;

}
