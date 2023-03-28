package goodshop.com.GoodsShop.model.dto.discount;
import goodshop.com.GoodsShop.model.dto.GoodDto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DiscountDto {

    private Integer id;
    private Integer discount;
    private LocalDateTime publishDate;
    private LocalDateTime expireDate;
    private Set<GoodDiscountDto> goods;


}
