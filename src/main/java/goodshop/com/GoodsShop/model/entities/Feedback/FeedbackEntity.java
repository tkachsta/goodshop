package goodshop.com.GoodsShop.model.entities.Feedback;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column(name = "date")
    private Timestamp pubDate;


}
