package goodshop.com.GoodsShop.model.entities.Good;
import goodshop.com.GoodsShop.model.entities.Acquiring.AcquiringEntity;
import goodshop.com.GoodsShop.model.entities.Company.CompanyEntity;
import goodshop.com.GoodsShop.model.entities.Discount.DiscountEntity;
import goodshop.com.GoodsShop.model.entities.Good.GoodProperties.GoodPropertiesEntity;
import goodshop.com.GoodsShop.model.entities.Rating.RatingEntity;
import goodshop.com.GoodsShop.model.entities.Stock.Stock2Good.Stock2Good;
import goodshop.com.GoodsShop.model.entities.Tags.Tag2Good;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter

@Table
@Entity(name = "goods")
public class GoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @Column(unique = true)
    private String slug;
    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;
    @Column
    @NotNull
    private Long price;
    @ManyToOne
    @JoinColumn(name = "id_discount", referencedColumnName = "id")
    private DiscountEntity discount;
    @ManyToOne
    @JoinColumn(name = "id_company", referencedColumnName = "id")
    @NotNull
    private CompanyEntity company;
    @ManyToOne
    @JoinColumn(name = "id_property", referencedColumnName = "id")
    @NotNull
    private GoodPropertiesEntity properties;
    @OneToMany(mappedBy = "good")
    private Set<RatingEntity> ratings;
    @OneToMany(mappedBy = "good")
    private Set<AcquiringEntity> acquiring;
    @OneToMany(mappedBy = "good")
    private Set<Stock2Good> stocks;
    @OneToMany(mappedBy = "good")
    private Set<Tag2Good> tags;


}
