package goodshop.com.GoodsShop.controllers;
import goodshop.com.GoodsShop.data.components.mappers.MapperToAcqusitionDto.MapperToAcquisitionDto;
import goodshop.com.GoodsShop.data.services.AcquisitionService;
import goodshop.com.GoodsShop.model.dto.GoodDto;
import goodshop.com.GoodsShop.model.dto.acqusition.AcquisitionDto;
import goodshop.com.GoodsShop.model.dto.requests.AcquisitionMovementDto;
import goodshop.com.GoodsShop.model.dto.responses.GoodApiResponse;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/acquisitions")
public class AcquisitionRestApiController {

    private final AcquisitionService acquisitionService;
    private final MapperToAcquisitionDto mapperToAcquisitionDto;

    @Operation(summary = "Get all acquisitions by type for currently auth user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acquisitions successfully found for currently auth user"),
            @ApiResponse(responseCode = "204", description = "Any acquisitions wasn't found for currently auth user")
    })
    @GetMapping("/show-for-auth-user")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<GoodApiResponse<AcquisitionDto>> getAcquisitionForAuthUser(@RequestParam("type") String type)
            throws ApiWrongParameterException {
        GoodApiResponse<AcquisitionDto> response = new GoodApiResponse<>();
        List<AcquisitionDto> acquisitionDtoList = acquisitionService.getAcquisitionsForAuthUser(type)
                .stream().map(mapperToAcquisitionDto::convertToAcquisitionDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + acquisitionDtoList.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(acquisitionDtoList);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Create new acquisition for currently auth user-buyer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acquisition successfully created for currently auth user"),
            @ApiResponse(responseCode = "204", description = "Couldn't create acquisition for currently auth user")
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<GoodApiResponse<AcquisitionDto>> createNewAcquisitionForBuyer(@RequestParam("goodId") String goodId)
            throws ApiWrongParameterException {
        GoodApiResponse<AcquisitionDto> response = new GoodApiResponse<>();
        List<AcquisitionDto> acquisitionDtoList = acquisitionService.createAcquisitionForBuyer(goodId)
                .stream().map(mapperToAcquisitionDto::convertToAcquisitionDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + acquisitionDtoList.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(acquisitionDtoList);
        return ResponseEntity.ok(response);

    }


    @Operation(summary = "Move defined acquisition from one status to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acquisition successfully moved to a new status"),
            @ApiResponse(responseCode = "204", description = "Couldn't move acquisition to a new status")
    })
    @PatchMapping("/move")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<GoodApiResponse<AcquisitionDto>> moveAcquisitionToNewStatus(@RequestBody AcquisitionMovementDto movementDto)
            throws ApiWrongParameterException {
        GoodApiResponse<AcquisitionDto> response = new GoodApiResponse<>();
        List<AcquisitionDto> acquisitionDtoList = acquisitionService.moveToNewStatus(movementDto)
                .stream().map(mapperToAcquisitionDto::convertToAcquisitionDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + acquisitionDtoList.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(acquisitionDtoList);
        return ResponseEntity.ok(response);

    }

    @Operation(summary = "Move defined acquisition from one status to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acquisition successfully moved to a new status"),
            @ApiResponse(responseCode = "204", description = "Couldn't move acquisition to a new status")
    })
    @GetMapping("/admin/show-buy-user-and-status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GoodApiResponse<AcquisitionDto>> showAcquisitionByUserAndStatus(
                                        @RequestParam("userId") Integer userId,
                                        @RequestParam("statuses") String statuses) throws ApiWrongParameterException {
        GoodApiResponse<AcquisitionDto> response = new GoodApiResponse<>();
        List<AcquisitionDto> acquisitionDtoList = acquisitionService.showByUserIdAndStatus(userId, statuses)
                .stream().map(mapperToAcquisitionDto::convertToAcquisitionDto).toList();
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + acquisitionDtoList.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(acquisitionDtoList);
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
