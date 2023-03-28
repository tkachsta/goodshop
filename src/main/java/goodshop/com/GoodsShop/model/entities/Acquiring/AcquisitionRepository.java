package goodshop.com.GoodsShop.model.entities.Acquiring;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcquisitionRepository extends JpaRepository<AcquiringEntity, Long> {
    List<AcquiringEntity> findAllByCurrentStatusInAndUserEntity(List<StatusType> status, UserEntity user);

}
