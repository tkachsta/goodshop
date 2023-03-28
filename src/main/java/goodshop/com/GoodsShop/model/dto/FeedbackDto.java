package goodshop.com.GoodsShop.model.dto;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class FeedbackDto {
    private String text;
    private Timestamp pubDate;
    private float value;
    private String username;

}
