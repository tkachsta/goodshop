package goodshop.com.GoodsShop.model.dto.requests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodRequestDto {
    private Integer id;
    private String name;
    private String description;
    private Long price;
    private Integer idCompany;
    private Integer idProperties;
    private Integer idDiscount;

}
