package goodshop.com.GoodsShop.data.services;

import goodshop.com.GoodsShop.model.entities.Acquiring.AcquiringEntity;
import goodshop.com.GoodsShop.model.entities.Acquiring.AcquiringHistory;
import goodshop.com.GoodsShop.model.entities.Acquiring.AcquisitionHistoryRepository;
import goodshop.com.GoodsShop.model.entities.Acquiring.StatusType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AcquisitionLoggingService {

    private final AcquisitionHistoryRepository historyRepository;
    public void logAcquisitionMovement(AcquiringEntity acquiring, StatusType from, StatusType to) {

        AcquiringHistory auditLog = new AcquiringHistory();
        auditLog.setDate(LocalDateTime.now());
        auditLog.setAcquiring(acquiring);
        auditLog.setCurrentPrice(acquiring.getPurchasePrice());
        auditLog.setFromStatus(from);
        auditLog.setToStatus(to);

        historyRepository.save(auditLog);

    }

}
