package goodshop.com.GoodsShop.model.entities.RolesPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipleGroupsRepository extends JpaRepository<PrincipleGroups, Integer> {

    PrincipleGroups findByCodeIs(String code);


}
