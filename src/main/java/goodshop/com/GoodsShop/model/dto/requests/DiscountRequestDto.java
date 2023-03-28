package goodshop.com.GoodsShop.model.dto.requests;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DiscountRequestDto {

    private Integer discount;
    private String expireDate;
    private String goods;


}
