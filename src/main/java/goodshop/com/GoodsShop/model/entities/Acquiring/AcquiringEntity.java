package goodshop.com.GoodsShop.model.entities.Acquiring;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "acquiring")
public class AcquiringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_good")
    private GoodEntity good;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusType currentStatus;
    @Column(name = "purchase_price")
    private Long purchasePrice;
    @Column
    private LocalDateTime date;


}
