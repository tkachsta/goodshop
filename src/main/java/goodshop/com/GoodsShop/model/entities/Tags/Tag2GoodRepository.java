package goodshop.com.GoodsShop.model.entities.Tags;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tag2GoodRepository extends JpaRepository<Tag2Good,Tag2GoodKey> {

    @Query("SELECT T2G.good FROM Tag2Good T2G WHERE T2G.tag.tagName IN :tag")
    List<GoodEntity> findAllGoodsByTag(@Param("tag") List<String> tag);

}
