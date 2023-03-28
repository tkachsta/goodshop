package goodshop.com.GoodsShop.data.services;
import goodshop.com.GoodsShop.model.dto.requests.AcquisitionMovementDto;
import goodshop.com.GoodsShop.model.entities.Acquiring.*;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.Good.GoodsRepository;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserRepository;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AcquisitionService extends AbstractAuthService {
    private final StatusTypeRepository statusTypeRepository;
    private final AcquisitionRepository acquisitionRepository;
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;
    private final AcquisitionLoggingService acquisitionLoggingService;
    private final MovementAllowanceRepository movementAllowanceRepository;

    public List<AcquiringEntity> getAcquisitionsForAuthUser(String type) throws ApiWrongParameterException {
        String[] types = type.split(",");
        List<StatusType> statusTypes = new ArrayList<>();
        for(String t : types) {
            Optional<StatusType> statusType = statusTypeRepository.findByCodeIs(t);
            if (statusType.isEmpty()) {
                throw new ApiWrongParameterException("There is no such code with status " + t);
            }
            statusTypes.add(statusType.get());
        }
        List<AcquiringEntity> acquiringEntities =
                acquisitionRepository.findAllByCurrentStatusInAndUserEntity(statusTypes, getCurrentUser());

        if (acquiringEntities.isEmpty()) {
            throw new ApiWrongParameterException("There are no acquisitions with defined statuses");
        }

        return acquiringEntities;
    }
    public List<AcquiringEntity>  createAcquisitionForBuyer(String goodId) throws ApiWrongParameterException {
        try {
            Integer integerId = Integer.parseInt(goodId);
            Optional<GoodEntity> goodEntity = goodsRepository.findById(integerId);
            if (goodEntity.isEmpty()) {
                throw new ApiWrongParameterException("There is no goods with id = " + integerId);
            }
            AcquiringEntity acquiring = new AcquiringEntity();
            Optional<UserEntity> user = userRepository.findByEmailIs(getCurrentUser().getEmail());
            Optional<StatusType> statusType = statusTypeRepository.findByCodeIs("CREATED");

            acquiring.setGood(goodEntity.get());
            acquiring.setPurchasePrice(goodEntity.get().getPrice());
            acquiring.setUserEntity(user.get());
            acquiring.setCurrentStatus(statusType.get());
            acquiring.setDate(LocalDateTime.now());

            acquisitionRepository.saveAndFlush(acquiring);
            acquisitionLoggingService.logAcquisitionMovement(acquiring, null, statusType.get());

            List<AcquiringEntity> list = new ArrayList<>();
            list.add(acquiring);
            return list;

        } catch (Exception ex) {
            throw new ApiWrongParameterException(ex.getLocalizedMessage());
        }


    }
    public List<AcquiringEntity>  moveToNewStatus(AcquisitionMovementDto movementDto) throws ApiWrongParameterException {
        Optional<AcquiringEntity> optionalAcquiring = acquisitionRepository.findById(movementDto.getAcquisitionId());
        if (optionalAcquiring.isEmpty()) {
            throw new ApiWrongParameterException("There is no acquisition with defined ID" + movementDto.getAcquisitionId());
        }
        AcquiringEntity acquiring = optionalAcquiring.get();
        if (!acquiring.getUserEntity().equals(getCurrentUser())) {
            throw new ApiWrongParameterException("You cannot move acquisition for another user");
        }
        Optional<StatusType> statusTo = statusTypeRepository.findByCodeIs(movementDto.getStatusTo());
        if (statusTo.isEmpty()) {
            throw new ApiWrongParameterException("Defined status " + movementDto.getStatusTo() + " doesn't exist");
        }
        StatusType statusFrom = acquiring.getCurrentStatus();
        Optional<StatusMovementAllowance> movementAllowance =
                movementAllowanceRepository.findByFromStatusAndToStatus(statusFrom, statusTo.get());
        if (movementAllowance.isEmpty()) {
            throw new ApiWrongParameterException("The rule of movement from status " + statusFrom.getCode() + ". To status " +
                    statusTo.get().getCode() + "is not defined");
        }
        if (!movementAllowance.get().isAllowed()) {
            throw new ApiWrongParameterException("To move from status " + statusFrom.getCode() + ". To status " +
                    statusTo.get().getCode() + "is not allowed by rule");
        }
        acquiring.setCurrentStatus(statusTo.get());
        acquisitionRepository.save(acquiring);
        acquisitionLoggingService.logAcquisitionMovement(acquiring, statusFrom, statusTo.get());
        List<AcquiringEntity> list = new ArrayList<>();
        list.add(acquiring);
        return list;
    }
    public List<AcquiringEntity> showByUserIdAndStatus(Integer userId, String statuses) throws ApiWrongParameterException {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ApiWrongParameterException("User doesn't not exist");
        }

        String[] types = statuses.split(",");
        List<StatusType> statusTypes = new ArrayList<>();
        for(String t : types) {
            Optional<StatusType> statusType = statusTypeRepository.findByCodeIs(t);
            if (statusType.isEmpty()) {
                throw new ApiWrongParameterException("There is no such code with status " + t);
            }
            statusTypes.add(statusType.get());
        }
        List<AcquiringEntity> acquiringEntities =
                acquisitionRepository.findAllByCurrentStatusInAndUserEntity(statusTypes, user.get());

        if (acquiringEntities.isEmpty()) {
            throw new ApiWrongParameterException("There are no acquisitions with defined statuses for defined user");
        }

        return acquiringEntities;
    }
}
