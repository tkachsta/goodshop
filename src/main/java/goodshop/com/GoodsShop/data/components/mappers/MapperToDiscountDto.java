package goodshop.com.GoodsShop.data.components.mappers;
import goodshop.com.GoodsShop.model.dto.discount.DiscountDto;
import goodshop.com.GoodsShop.model.dto.discount.GoodDiscountDto;
import goodshop.com.GoodsShop.model.dto.discount.StockGoodsDto;
import goodshop.com.GoodsShop.model.entities.Discount.DiscountEntity;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.Stock.Stock2Good.Stock2Good;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
@AllArgsConstructor
public class MapperToDiscountDto {
    public DiscountDto convertToDiscountDto(DiscountEntity discountEntity) {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setId(discountEntity.getId());
        discountDto.setDiscount(discountEntity.getDiscount());
        discountDto.setPublishDate(discountEntity.getPublishDate());
        discountDto.setExpireDate(discountEntity.getExpireDate());
        discountDto.setGoods(discountEntity.getGoodEntities().stream().map(this::convertToGoodDto).collect(Collectors.toSet()));

        return discountDto;
    }
    private GoodDiscountDto convertToGoodDto(GoodEntity goodEntity) {
        GoodDiscountDto goodDto = new GoodDiscountDto();
        goodDto.setId(goodEntity.getId());
        goodDto.setName(goodEntity.getName());
        goodDto.setStocks(goodEntity.getStocks().stream().map(this::convertToStockDto).toList());

        return goodDto;
    }
    private StockGoodsDto convertToStockDto(Stock2Good stock2Good) {

        StockGoodsDto stockGoodsDto = new StockGoodsDto();
        stockGoodsDto.setId(stock2Good.getStock().getId());
        stockGoodsDto.setName(stock2Good.getStock().getStockName());
        stockGoodsDto.setCity(stock2Good.getStock().getCity());
        stockGoodsDto.setAmount(stock2Good.getValue());

        return stockGoodsDto;
    }

}
