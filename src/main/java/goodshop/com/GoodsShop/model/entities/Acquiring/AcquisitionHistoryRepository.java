package goodshop.com.GoodsShop.model.entities.Acquiring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquisitionHistoryRepository extends JpaRepository<AcquiringHistory, Long> {

    AcquiringHistory findTop1ByAcquiringOrderByDateDesc(AcquiringEntity acquiring);


}
