package goodshop.com.GoodsShop.controllers;
import goodshop.com.GoodsShop.data.services.UserRegisterService;
import goodshop.com.GoodsShop.model.dto.GoodDto;
import goodshop.com.GoodsShop.model.dto.register.ContactConfirmationPayload;
import goodshop.com.GoodsShop.model.dto.register.ContactConfirmationResponse;
import goodshop.com.GoodsShop.model.dto.register.RegistrationForm;
import goodshop.com.GoodsShop.model.dto.responses.FalseResponse;
import goodshop.com.GoodsShop.model.dto.responses.GoodApiResponse;
import goodshop.com.GoodsShop.model.dto.responses.TrueResponse;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthRestApiController {

    private final UserRegisterService userRegisterService;

    @PostMapping("/api/registration/buyer-reg")
    @ResponseBody
    ResponseEntity<?> handleBuyerRegistration (@RequestBody RegistrationForm registrationForm) {
        if (userRegisterService.registerNewBuyer(registrationForm)) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        };
        return new ResponseEntity<>(new FalseResponse(false,
                "Provided email is already taken by another user"), HttpStatus.OK);
    }

    @PostMapping("/api/admin/registration/seller-reg")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    ContactConfirmationResponse handleSellerRegistration (@RequestBody RegistrationForm registrationForm) throws ApiWrongParameterException {
        userRegisterService.registerNewSeller(registrationForm);
        return new ContactConfirmationResponse("true");
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
