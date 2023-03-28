package goodshop.com.GoodsShop.data.services;

import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserRepository;
import goodshop.com.GoodsShop.security.ShopUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


public abstract class AbstractAuthService {



    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken);
    }


    public UserEntity getCurrentUser() {
        ShopUserDetails userDetails =
                (ShopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUserEntity();
    }

}
