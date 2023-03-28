package goodshop.com.GoodsShop.model.entities.Acquiring;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "acquiring_history")
public class AcquiringHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private AcquiringEntity acquiring;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private StatusType fromStatus;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private StatusType toStatus;
    @Column(name = "current_price")
    private Long currentPrice;
    @Column
    private LocalDateTime date;


}
