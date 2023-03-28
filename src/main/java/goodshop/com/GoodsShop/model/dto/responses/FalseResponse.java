package goodshop.com.GoodsShop.model.dto.responses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FalseResponse {
    private boolean response;
    private String message;

}
