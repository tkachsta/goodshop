package goodshop.com.GoodsShop.model.dto.requests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcquisitionMovementDto {
    private Long acquisitionId;
    private String statusTo;

}
