package goodshop.com.GoodsShop.data.services;
import goodshop.com.GoodsShop.model.dto.requests.GoodRequestDto;
import goodshop.com.GoodsShop.model.entities.Company.CompanyEntity;
import goodshop.com.GoodsShop.model.entities.Company.CompanyRepository;
import goodshop.com.GoodsShop.model.entities.Good.GoodEntity;
import goodshop.com.GoodsShop.model.entities.Good.GoodProperties.GoodPropertiesEntity;
import goodshop.com.GoodsShop.model.entities.Good.GoodProperties.GoodPropertiesRepository;
import goodshop.com.GoodsShop.model.entities.Good.GoodsRepository;
import goodshop.com.GoodsShop.model.entities.Tags.Tag2GoodRepository;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final CompanyRepository companyRepository;
    private final Tag2GoodRepository tag2GoodRepository;
    private final GoodPropertiesRepository goodPropertiesRepository;



    public List<GoodEntity> getGoodBySlug(String slug) throws ApiWrongParameterException {
        if (slug.length() <= 5) {
            throw new ApiWrongParameterException("Wrong values passed as good slug parameter");
        } else {
            List<GoodEntity> goodEntities = goodsRepository.findAllBySlugIs(slug);
            if (goodEntities.size() == 1) {
                return goodEntities;
            }
            if (goodEntities.size() > 1) {
                throw new ApiWrongParameterException("Find more than one unique slug");
            }
            throw new ApiWrongParameterException("not a single good found by request");
        }
    }
    public List<GoodEntity> getGoodsByName(String name) throws ApiWrongParameterException {
        if (name.length() <= 1) {
            throw new ApiWrongParameterException("Wrong values passed to name parameter");
        } else {
            List<GoodEntity> goodEntities = goodsRepository.findAllByNameContains(name);
            if (goodEntities.size() >= 1) {
                return goodEntities;
            }
            throw new ApiWrongParameterException("not a single good found by request");
        }
    }
    public List<GoodEntity> getGoodsByMaxDiscount() throws ApiWrongParameterException {
        List<GoodEntity> goodEntities = goodsRepository.findAllByMaxDiscount();
        if (goodEntities.size() >= 1) {
            return goodEntities;
        }
        throw new ApiWrongParameterException("there are no discounts at this time");
    }
    public List<GoodEntity> getGoodsByDiscountInterval(String min, String max) throws ApiWrongParameterException {

        try {
            Integer minInt = Integer.parseInt(min);
            Integer maxInt = Integer.parseInt(max);
            List<GoodEntity> goodEntities = goodsRepository.findAllByDiscountInterval(minInt, maxInt);
            if (goodEntities.size() >= 1) {
                return goodEntities;
            }
        } catch (Exception exception) {
            throw new ApiWrongParameterException(exception.getLocalizedMessage());
        }
        throw new ApiWrongParameterException("there are no goods within defined interval");

    }
    public List<GoodEntity> getGoodsByPriceInterval(String min, String max) throws ApiWrongParameterException {

        try {
            Integer minInt = Integer.parseInt(min);
            Integer maxInt = Integer.parseInt(max);
            List<GoodEntity> goodEntities = goodsRepository.findAllByPriceInterval(minInt, maxInt);
            if (goodEntities.size() >= 1) {
                return goodEntities;
            }
        } catch (Exception exception) {
            throw new ApiWrongParameterException(exception.getLocalizedMessage());
        }
        throw new ApiWrongParameterException("there are no goods within defined interval");

    }
    public List<GoodEntity> getGoodsByTag(String tag) throws ApiWrongParameterException {
        List<String> tags = Arrays.asList(tag.split(","));
        if (tag.length() <= 5) {
            throw new ApiWrongParameterException("Wrong values passed to name parameter");
        } else {
            List<GoodEntity> goodEntities = tag2GoodRepository.findAllGoodsByTag(tags);
            if (goodEntities.size() >= 1) {
                return goodEntities;
            }
            throw new ApiWrongParameterException("not a single good found by request");
        }
    }
    public List<GoodEntity> createNewGood(GoodRequestDto goodDto) throws ApiWrongParameterException {

        GoodEntity goodEntity = new GoodEntity();
        Optional<CompanyEntity> companyEntity = companyRepository.findById(goodDto.getIdCompany());
        if(companyEntity.isPresent()) {
            goodEntity.setCompany(companyEntity.get());
        } else {
            throw new ApiWrongParameterException("There is not company with associated ID. " +
                    "Please choose existing or create a new one");
        }

        Optional<GoodPropertiesEntity> properties =  goodPropertiesRepository.findById(goodDto.getIdProperties());
        if(properties.isPresent()) {
            goodEntity.setProperties(properties.get());
        } else {
            throw new ApiWrongParameterException("There is not properties with associated ID. " +
                    "Please choose existing or create a new one");
        }

        goodEntity.setName(goodDto.getName());
        goodEntity.setPrice(goodDto.getPrice());
        goodEntity.setDescription(goodDto.getDescription());
        goodsRepository.save(goodEntity);
        List<GoodEntity> list = new ArrayList<>();
        list.add(goodEntity);
        return list;
    }
    public List<GoodEntity> updateExistingGood(GoodRequestDto goodDto) throws ApiWrongParameterException {
        try {
            Optional<GoodEntity> good = goodsRepository.findById(goodDto.getId());

            if (good.isEmpty()) {
                throw new ApiWrongParameterException("Good is missing with ID = " + goodDto.getId());
            }

            Integer id_company = goodDto.getIdCompany();
            if (id_company != null) {
                Optional<CompanyEntity> companyEntity = companyRepository.findById(id_company);
                if (companyEntity.isEmpty()) {
                    throw new ApiWrongParameterException("There is not company with associated ID. " +
                            "Please choose existing or create a new one");
                }
                good.get().setCompany(companyEntity.get());
            }

            Integer id_properties = goodDto.getIdProperties();
            if(id_properties != null) {
                Optional<GoodPropertiesEntity> properties = goodPropertiesRepository.findById(id_properties);
                if(properties.isEmpty()) {
                    throw new ApiWrongParameterException("There is not properties with associated ID. " +
                            "Please choose existing or create a new one");
                }
                good.get().setProperties(properties.get());
            }

            if(goodDto.getName() != null) {
                good.get().setName(goodDto.getName());
            }
            if(goodDto.getDescription() != null) {
                good.get().setDescription(goodDto.getDescription());
            }
            if(goodDto.getPrice() != null) {
                good.get().setPrice(goodDto.getPrice());
            }

            goodsRepository.save(good.get());
            List<GoodEntity> list = new ArrayList<>();
            list.add(good.get());
            return list;

        } catch (Exception e) {
            throw new ApiWrongParameterException(e.getLocalizedMessage());
        }
    }


}
