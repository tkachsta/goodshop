package goodshop.com.GoodsShop.model.entities.Tags;

import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tag2good")
public class Tag2Good {

    @EmbeddedId
    private Tag2GoodKey tag2GoodKey;
    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "id_tag")
    private TagEntity tag;
    @ManyToOne
    @MapsId("good_id")
    @JoinColumn(name = "id_good")
    private GoodEntity good;


}
