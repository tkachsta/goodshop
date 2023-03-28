package goodshop.com.GoodsShop.model.entities.Acquiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovementAllowanceRepository extends JpaRepository<StatusMovementAllowance, Integer> {

    Optional<StatusMovementAllowance> findByFromStatusAndToStatus(StatusType fromStatus, StatusType toStatus);

}
