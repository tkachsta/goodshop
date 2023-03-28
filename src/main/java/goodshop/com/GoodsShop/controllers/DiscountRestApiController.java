package goodshop.com.GoodsShop.controllers;
import goodshop.com.GoodsShop.data.components.mappers.MapperToDiscountDto;
import goodshop.com.GoodsShop.data.services.DiscountService;
import goodshop.com.GoodsShop.model.dto.GoodDto;
import goodshop.com.GoodsShop.model.dto.discount.DiscountDto;
import goodshop.com.GoodsShop.model.dto.requests.DiscountRequestDto;
import goodshop.com.GoodsShop.model.dto.requests.GoodRequestDto;
import goodshop.com.GoodsShop.model.dto.responses.GoodApiResponse;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/discounts")
public class DiscountRestApiController {

    DiscountService discountService;
    MapperToDiscountDto mapperToDiscountDto;


    @Operation(summary = "Get discount by discount amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Discount successfully found by amount"),
            @ApiResponse(responseCode = "204", description = "Any discount wasn't found by defined amount")
    })
    @GetMapping("/by-discount")
    public ResponseEntity<GoodApiResponse<DiscountDto>> discountByValue(@RequestParam("discount") String discount)
            throws ApiWrongParameterException {
        GoodApiResponse<DiscountDto> response = new GoodApiResponse<>();
        List<DiscountDto> discountEntities = discountService.getDiscountByAmount(discount)
                .stream().map(mapperToDiscountDto::convertToDiscountDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + discountEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(discountEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get discounts by its interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Discounts successfully found within defined interval"),
            @ApiResponse(responseCode = "204", description = "Any discount wasn't found within defined interval")
    })
    @GetMapping("/by-discount-interval")
    public ResponseEntity<GoodApiResponse<DiscountDto>> discountsWithinInterval (@RequestParam("min") String min,
                                                                                @RequestParam("max") String max) throws ApiWrongParameterException {
        GoodApiResponse<DiscountDto> response = new GoodApiResponse<>();
        List<DiscountDto> goodEntities = discountService.getDiscountByInterval(min, max).stream().map(mapperToDiscountDto::convertToDiscountDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get expired discounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expired discounts successfully found"),
            @ApiResponse(responseCode = "204", description = "Any expired discount wasn't found")
    })
    @GetMapping("/by-expired")
    public ResponseEntity<GoodApiResponse<DiscountDto>> getExpiredDiscounts() throws ApiWrongParameterException {
        GoodApiResponse<DiscountDto> response = new GoodApiResponse<>();
        List<DiscountDto> goodEntities = discountService.getExpiredDiscounts().stream().map(mapperToDiscountDto::convertToDiscountDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get active discounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Active discounts successfully found"),
            @ApiResponse(responseCode = "204", description = "Any  active discount wasn't found")
    })
    @GetMapping("/by-active")
    public ResponseEntity<GoodApiResponse<DiscountDto>> getActiveDiscounts() throws ApiWrongParameterException {
        GoodApiResponse<DiscountDto> response = new GoodApiResponse<>();
        List<DiscountDto> goodEntities = discountService.getActiveDiscounts().stream().map(mapperToDiscountDto::convertToDiscountDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create new discount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New discount successfully created"),
            @ApiResponse(responseCode = "204", description = "A new discount couldn't be created")
    })
    @PostMapping("/create")
    public ResponseEntity<GoodApiResponse<DiscountDto>> createNewGood(@RequestBody DiscountRequestDto requestDto) throws ApiWrongParameterException {
        GoodApiResponse<DiscountDto> response = new GoodApiResponse<>();
        Set<DiscountDto> goodEntities =  discountService.createNewDiscount(requestDto)
                .stream().map(mapperToDiscountDto::convertToDiscountDto).collect(Collectors.toSet());
        response.setDebugMessage("Successful request");
        response.setMessage("New discount entity created");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add goods to discount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully added to discount"),
            @ApiResponse(responseCode = "204", description = "Goods can't be added to discount")
    })
    @PostMapping("/add-goods")
    public ResponseEntity<GoodApiResponse<DiscountDto>> addGoodsToDiscount(@RequestParam("discountId") String id,
                                                                           @RequestParam("goodIds") String goodIds) throws ApiWrongParameterException {
        GoodApiResponse<DiscountDto> response = new GoodApiResponse<>();
        List<DiscountDto> goodEntities =  discountService.addNewGoodsToDiscount(id, goodIds).stream()
                .map(mapperToDiscountDto::convertToDiscountDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Goods successfully added to discount");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }




    @ExceptionHandler(ApiWrongParameterException.class)
    public ResponseEntity<GoodApiResponse<GoodDto>> handleShopApiWrongParameterException(Exception exception) {
        return new ResponseEntity<>(
                new GoodApiResponse<>(
                        HttpStatus.BAD_REQUEST, "Bad parameter value", exception), HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GoodApiResponse<GoodDto>> handleMissingServletParameterException(Exception exception) {
        return new ResponseEntity<>(
                new GoodApiResponse<>(
                        HttpStatus.BAD_REQUEST, "Missing required parameter", exception), HttpStatus.BAD_REQUEST
        );
    }


}
