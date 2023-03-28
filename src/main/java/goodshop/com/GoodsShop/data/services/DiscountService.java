package goodshop.com.GoodsShop.data.services;
import goodshop.com.GoodsShop.model.dto.requests.DiscountRequestDto;
import goodshop.com.GoodsShop.model.entities.Discount.DiscountEntity;
import goodshop.com.GoodsShop.model.entities.Discount.DiscountRepository;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.Good.GoodsRepository;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final GoodsRepository goodsRepository;

    public List<DiscountEntity> getDiscountByAmount(String id) throws ApiWrongParameterException {

        try {
            Integer idInt = Integer.parseInt(id);
            List<DiscountEntity> discountEntity = discountRepository.findAllByDiscount(idInt);
            if (discountEntity.isEmpty()) {
                throw new ApiWrongParameterException("There is no such discount value = " + id);
            }
            return discountEntity;

        } catch (Exception exception) {
            throw new ApiWrongParameterException(exception.getLocalizedMessage());
        }




    }
    public List<DiscountEntity> getDiscountByInterval(String min, String max) throws ApiWrongParameterException {

        try {
            Integer minInt = Integer.parseInt(min);
            Integer maxInt = Integer.parseInt(max);
            List<DiscountEntity> discountEntities = discountRepository.findAllByInterval(minInt, maxInt);
            if (discountEntities.isEmpty()) {
                throw new ApiWrongParameterException("There are no discount values in defined interval");
            }
            return discountEntities;

        } catch (Exception exception) {
            throw new ApiWrongParameterException(exception.getLocalizedMessage());
        }
    }
    public List<DiscountEntity> getExpiredDiscounts() throws ApiWrongParameterException {

        List<DiscountEntity> discountEntities = discountRepository.findAllByExpireDateBefore(LocalDateTime.now());
        if (discountEntities.isEmpty()) {
            throw new ApiWrongParameterException("There are no expired discounts");
        }
        return discountEntities;

    }
    public List<DiscountEntity> getActiveDiscounts() throws ApiWrongParameterException {

        List<DiscountEntity> discountEntities = discountRepository.findAllByExpireDateAfter(LocalDateTime.now());
        if (discountEntities.isEmpty()) {
            throw new ApiWrongParameterException("There are no active discounts");
        }
        return discountEntities;

    }
    public List<DiscountEntity> createNewDiscount(DiscountRequestDto requestDto) throws ApiWrongParameterException {

        DiscountEntity discountEntity = new DiscountEntity();
        String[] goods = requestDto.getGoods().split(",");
        try {
            Set<GoodEntity> goodEntities = new HashSet<>();
            for (String good: goods) {
                Integer goodId = Integer.parseInt(good);
                Optional<GoodEntity> goodEntity = goodsRepository.findById(goodId);
                if (goodEntity.isEmpty()) {
                    throw new ApiWrongParameterException("There is no good with defined id = " + goodId);
                }
                if (goodEntity.get().getDiscount() != null) {
                    throw new ApiWrongParameterException("There is already discount for defined good id = " + good + ". " +
                            "Please delete previous discount");
                }
                goodEntities.add(goodEntity.get());
            }

            discountEntity.setDiscount(requestDto.getDiscount());
            discountEntity.setPublishDate(LocalDateTime.now());
            discountEntity.setExpireDate(LocalDateTime.parse(
                    requestDto.getExpireDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            discountEntity.setGoodEntities(goodEntities);
            discountRepository.save(discountEntity);

            List<DiscountEntity> discounts = new ArrayList<>();
            discounts.add(discountEntity);

            return discounts;

        } catch (Exception exception) {
            throw new ApiWrongParameterException(exception.getLocalizedMessage());
        }
    }
    public List<DiscountEntity> addNewGoodsToDiscount(String discountId, String goods) throws ApiWrongParameterException {
        String[] list = goods.split(",");
        try {
            Integer id = Integer.parseInt(discountId);
            Optional<DiscountEntity> discount = discountRepository.findById(id);
            if (discount.isEmpty()) {
                throw new ApiWrongParameterException("There is no discount with defined id = " + id);
            }

            for (String good: list) {
                Integer goodId = Integer.parseInt(good);
                Optional<GoodEntity> goodEntity = goodsRepository.findById(goodId);
                if (goodEntity.isEmpty()) {
                    throw new ApiWrongParameterException("There is no good with defined id = " + goodId);
                } else {
                    goodEntity.get().setDiscount(discount.get());
                    goodsRepository.save(goodEntity.get());
                }
            }
            List<DiscountEntity> discounts = new ArrayList<>();
            discounts.add(discount.get());
            return discounts;

        } catch (Exception exception) {
            throw new ApiWrongParameterException(exception.getLocalizedMessage());
        }

    }


}
