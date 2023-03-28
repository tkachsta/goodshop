package goodshop.com.GoodsShop.model.entities.Acquiring;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "status_movement_allowance")
@Entity
@Getter
@Setter
public class StatusMovementAllowance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_status", referencedColumnName = "id")
    private StatusType fromStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_status", referencedColumnName = "id")
    private StatusType toStatus;

    @Column(name = "is_allowed")
    private boolean isAllowed;



}
