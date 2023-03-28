package goodshop.com.GoodsShop.model.entities.Rating;
import goodshop.com.GoodsShop.model.entities.Feedback.FeedbackEntity;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "rating")
public class RatingEntity {
    @EmbeddedId
    private RatingKey ratingKey;
    @ManyToOne
    @MapsId("goodId")
    @JoinColumn(name = "id_good")
    private GoodEntity good;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    private UserEntity user;
    @OneToOne
    @JoinColumn(name = "id_feedback")
    private FeedbackEntity feedback;
    @Column
    private float value;
    @Column
    private LocalDateTime date;

}
