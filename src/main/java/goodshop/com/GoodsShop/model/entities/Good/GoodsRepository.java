package goodshop.com.GoodsShop.model.entities.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<GoodEntity, Integer> {
    List<GoodEntity> findAllBySlugIs(String slug);
    List<GoodEntity> findAllByNameContains(String name);
    @Query("SELECT g FROM goods g WHERE g.discount.discount = (SELECT MAX(go.discount.discount) FROM goods go)")
    List<GoodEntity> findAllByMaxDiscount();
    @Query("SELECT g FROM goods g WHERE g.discount.discount BETWEEN :min AND :max ORDER BY g.discount.discount DESC")
    List<GoodEntity> findAllByDiscountInterval(@Param("min") Integer min, @Param("max") Integer max);
    @Query("SELECT g FROM goods g WHERE g.price BETWEEN :min AND :max ORDER BY g.price DESC")
    List<GoodEntity> findAllByPriceInterval(@Param("min") Integer min, @Param("max") Integer max);


}
