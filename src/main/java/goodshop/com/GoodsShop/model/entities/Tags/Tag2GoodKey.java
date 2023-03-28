package goodshop.com.GoodsShop.model.entities.Tags;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Tag2GoodKey implements Serializable {

    @Column(name = "id_tag")
    private Integer tagId;

    @Column(name = "id_good")
    private Integer goodId;


}
