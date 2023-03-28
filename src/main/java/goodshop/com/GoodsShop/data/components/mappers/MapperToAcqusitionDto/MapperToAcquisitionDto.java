package goodshop.com.GoodsShop.data.components.mappers.MapperToAcqusitionDto;

import goodshop.com.GoodsShop.model.dto.GoodDto;
import goodshop.com.GoodsShop.model.dto.acqusition.AcquisitionDto;
import goodshop.com.GoodsShop.model.dto.acqusition.UserDto;
import goodshop.com.GoodsShop.model.entities.Acquiring.AcquiringEntity;
import goodshop.com.GoodsShop.model.entities.Acquiring.AcquisitionHistoryRepository;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;



@AllArgsConstructor
@Component
public class MapperToAcquisitionDto {

    private final AcquisitionHistoryRepository acquisitionHistoryRepository;

    public AcquisitionDto convertToAcquisitionDto(AcquiringEntity acquiring) {
        AcquisitionDto acquisitionDto = new AcquisitionDto();
        acquisitionDto.setGoodDto(convertToGoodDto(acquiring.getGood()));
        acquisitionDto.setUserDto(convertToUserDto(acquiring.getUserEntity()));
        acquisitionDto.setDate(getLastDate(acquiring));
        acquisitionDto.setCurrentPrice(acquiring.getPurchasePrice());
        acquisitionDto.setCurrentStatus(acquiring.getCurrentStatus().getCode());

        return acquisitionDto;
    }
    private AcquisitionGoodDto convertToGoodDto(GoodEntity good) {
        AcquisitionGoodDto goodDto = new AcquisitionGoodDto();
        goodDto.setId(good.getId());
        goodDto.setName(good.getName());
        goodDto.setCompany(good.getCompany().getName());

        return goodDto;
    }
    private UserDto convertToUserDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
   private LocalDateTime getLastDate(AcquiringEntity acquiring) {
        return acquisitionHistoryRepository
                .findTop1ByAcquiringOrderByDateDesc(acquiring).getDate();
    }



}
