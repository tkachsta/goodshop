package goodshop.com.GoodsShop.model.entities.Rating;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RatingKey implements Serializable {
    @Column(name = "id_good")
    private Integer goodId;
    @Column(name = "id_user")
    private Integer userId;


}
