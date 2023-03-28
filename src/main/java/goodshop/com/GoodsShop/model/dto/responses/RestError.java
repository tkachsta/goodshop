package goodshop.com.GoodsShop.model.dto.responses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestError {

    private String status;
    private String message;


}
