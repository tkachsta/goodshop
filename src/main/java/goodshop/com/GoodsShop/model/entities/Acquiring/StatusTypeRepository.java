package goodshop.com.GoodsShop.model.entities.Acquiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusTypeRepository extends JpaRepository<StatusType, Integer> {

    Optional<StatusType> findByCodeIs(String code);

}
