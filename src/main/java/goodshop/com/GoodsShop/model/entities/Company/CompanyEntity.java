package goodshop.com.GoodsShop.model.entities.Company;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name= "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String logo;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity user;
    @OneToMany(mappedBy = "company")
    private Set<GoodEntity> companyGoods;

}
