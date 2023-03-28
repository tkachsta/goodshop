package goodshop.com.GoodsShop.model.entities.Good.GoodProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodPropertiesRepository extends JpaRepository<GoodPropertiesEntity, Integer> {
}
