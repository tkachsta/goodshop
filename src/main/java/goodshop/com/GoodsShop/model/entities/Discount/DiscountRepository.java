package goodshop.com.GoodsShop.model.entities.Discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiscountRepository  extends JpaRepository<DiscountEntity, Integer> {
    List<DiscountEntity> findAllByDiscount(Integer value);
    @Query("SELECT d FROM discount d WHERE d.discount BETWEEN :min AND :max ORDER BY d.discount")
    List<DiscountEntity> findAllByInterval(@Param("min") Integer min, @Param("max") Integer max);
    List<DiscountEntity> findAllByExpireDateBefore(LocalDateTime date);
    List<DiscountEntity> findAllByExpireDateAfter(LocalDateTime date);

}
