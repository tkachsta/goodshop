package goodshop.com.GoodsShop.model.entities.RolesPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer> {


    Optional<UserEntity> findByEmailIs(String email);

}
