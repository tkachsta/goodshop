package goodshop.com.GoodsShop.model.dto.acqusition;
import goodshop.com.GoodsShop.data.components.mappers.MapperToAcqusitionDto.AcquisitionGoodDto;
import goodshop.com.GoodsShop.model.dto.GoodDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AcquisitionDto {

    private AcquisitionGoodDto goodDto;
    private UserDto userDto;
    private String currentStatus;
    private Long currentPrice;
    private LocalDateTime date;

}
