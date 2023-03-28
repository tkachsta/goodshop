package goodshop.com.GoodsShop.model.dto;
import goodshop.com.GoodsShop.model.dto.discount.DiscountDto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GoodDto {
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private Long price;
    private String company;
    private DiscountDto discount;
    private PropertiesDto properties;
    private RatingDto rating;
    private List<FeedbackDto> feedbacks;
    private List<String> tags;
    private List<StockDto> stocks;

}
