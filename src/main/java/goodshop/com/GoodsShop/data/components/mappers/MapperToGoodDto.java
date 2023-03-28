package goodshop.com.GoodsShop.data.components.mappers;
import goodshop.com.GoodsShop.model.dto.*;
import goodshop.com.GoodsShop.model.dto.discount.DiscountDto;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.Rating.RatingEntity;
import goodshop.com.GoodsShop.model.entities.Stock.Stock2Good.Stock2Good;
import goodshop.com.GoodsShop.model.entities.Stock.StockEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@AllArgsConstructor
public class MapperToGoodDto {
    private final ModelMapper modelMapper;
    public GoodDto convertToDto(GoodEntity good) {

        GoodDto goodDto = modelMapper.map(good, GoodDto.class);
        goodDto.setId(goodDto.getId());
        goodDto.setCompany(good.getCompany().getName());
        goodDto.setSlug(good.getSlug());
        goodDto.setName(good.getName());
        goodDto.setPrice(good.getPrice());
        goodDto.setDescription(good.getDescription());

        if (good.getTags() != null) {
            goodDto.setTags(good.getTags().stream().map(o -> o.getTag().getTagName()).toList());
        }
        if(good.getDiscount() != null) {
            DiscountDto discountDto = modelMapper.map(good.getDiscount(), DiscountDto.class);
            goodDto.setDiscount(discountDto);
        }
        if(good.getTags() != null) {
            goodDto.setStocks(good.getStocks().stream().map(this::toStockDto).toList());
        }
        if(good.getRatings() != null) {
            goodDto.setRating(ratingDto(good.getRatings()));
            goodDto.setFeedbacks(good.getRatings().stream().map(this::toFeedbackDto).toList());
        }

        PropertiesDto propertiesDto = modelMapper.map(good.getProperties(), PropertiesDto.class);
        goodDto.setProperties(propertiesDto);

        return goodDto;
    }
    private StockDto toStockDto(Stock2Good stock2Good) {
        StockDto stockDto = new StockDto();
        StockEntity stock = stock2Good.getStock();
        stockDto.setStockName(stock.getStockName());
        stockDto.setCity(stock.getCity());
        stockDto.setAmount(stock2Good.getValue());
        return stockDto;
    }
    private RatingDto ratingDto(Set<RatingEntity> ratings) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setRatingAmount(ratings.size());
        ratingDto.setAverageAmount((float) ratings.stream()
                .mapToDouble(RatingEntity::getValue).average().orElse(Double.NaN));
        return ratingDto;
    }
    private FeedbackDto toFeedbackDto(RatingEntity rating) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setText(rating.getFeedback().getText());
        feedbackDto.setUsername(rating.getUser().getUsername());
        feedbackDto.setValue(rating.getValue());
        feedbackDto.setPubDate(rating.getFeedback().getPubDate());
        return feedbackDto;
    }

}
