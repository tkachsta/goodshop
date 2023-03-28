package goodshop.com.GoodsShop.controllers;
import goodshop.com.GoodsShop.data.components.mappers.MapperToGoodDto;
import goodshop.com.GoodsShop.data.services.GoodsService;
import goodshop.com.GoodsShop.model.dto.GoodDto;
import goodshop.com.GoodsShop.model.dto.requests.GoodRequestDto;
import goodshop.com.GoodsShop.model.dto.responses.GoodApiResponse;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodRestApiController {

    private final GoodsService goodsService;
    private final MapperToGoodDto mapperToGoodDto;
    public GoodRestApiController(GoodsService goodsService,
                                 MapperToGoodDto mapperToGoodDto) {
        this.goodsService = goodsService;
        this.mapperToGoodDto = mapperToGoodDto;
    }

    @Operation(summary = "Get product by unique slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good successfully found by unique slug"),
            @ApiResponse(responseCode = "204", description = "Any good wasn't found by unique slug")
    })
    @GetMapping("/by-slug")
    public ResponseEntity<GoodApiResponse<GoodDto>> goodBySlug(@RequestParam("slug") String slug)
            throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities = goodsService
                .getGoodBySlug(slug).stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get product by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully found by containing name"),
            @ApiResponse(responseCode = "204", description = "Any good wasn't found by name containing")
    })
    @GetMapping("/by-name")
    public ResponseEntity<GoodApiResponse<GoodDto>> goodsByName(@RequestParam("name") String name)
            throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities = goodsService.getGoodsByName(name).stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get product by highest discount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully found by highest discount"),
            @ApiResponse(responseCode = "204", description = "There are no active discounts for any good")
    })
    @GetMapping("/by-max-discount")
    public ResponseEntity<GoodApiResponse<GoodDto>> goodsByHighestDiscount()
            throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities = goodsService.getGoodsByMaxDiscount().stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get product by discount interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully found within defined interval"),
            @ApiResponse(responseCode = "204", description = "Any good wasn't found within defined interval")
    })
    @GetMapping("/by-discount-interval")
    public ResponseEntity<GoodApiResponse<GoodDto>> goodsByDiscountInterval(
            @RequestParam("min") String min, @RequestParam("max") String max) throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities = goodsService.getGoodsByDiscountInterval(min, max).stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get product by price interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully found within defined interval"),
            @ApiResponse(responseCode = "204", description = "Any good wasn't found within defined interval")
    })
    @GetMapping("/by-price-interval")
    public ResponseEntity<GoodApiResponse<GoodDto>> goodsByPriceInterval(
            @RequestParam("min") String min, @RequestParam("max") String max) throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities = goodsService.getGoodsByPriceInterval(min, max).stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get product by tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully found within for defined tag"),
            @ApiResponse(responseCode = "204", description = "Any good wasn't found for defined tag")
    })
    @GetMapping("/by-tag")
    public ResponseEntity<GoodApiResponse<GoodDto>> goodsByTag(@RequestParam("tag") String tag)
            throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities = goodsService.getGoodsByTag(tag).stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + goodEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Create new good")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New good successfully created"),
            @ApiResponse(responseCode = "204", description = "A new good couldn't be created")
    })
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_SELLER'})")
    public ResponseEntity<GoodApiResponse<GoodDto>> createNewGood(@RequestBody GoodRequestDto goodRequestDto) throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities =  goodsService.createNewGood(goodRequestDto).stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("New good entity created");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(goodEntities);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Update existing good")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existing good was successfully updated "),
            @ApiResponse(responseCode = "204", description = "Couldn't update existing good")
    })
    @PatchMapping ("/update")
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_SELLER'})")
    public ResponseEntity<GoodApiResponse<GoodDto>> updateExistingGood(@RequestBody GoodRequestDto goodRequestDto) throws ApiWrongParameterException {
        GoodApiResponse<GoodDto> response = new GoodApiResponse<>();
        List<GoodDto> goodEntities =  goodsService.updateExistingGood(goodRequestDto).stream().map(mapperToGoodDto::convertToDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Existing good successfully updated");
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
