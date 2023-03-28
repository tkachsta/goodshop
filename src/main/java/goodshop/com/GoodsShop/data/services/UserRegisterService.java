package goodshop.com.GoodsShop.data.services;
import goodshop.com.GoodsShop.model.dto.register.ContactConfirmationPayload;
import goodshop.com.GoodsShop.model.dto.register.ContactConfirmationResponse;
import goodshop.com.GoodsShop.model.dto.register.RegistrationForm;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.PrincipleGroups;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.PrincipleGroupsRepository;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserRepository;
import goodshop.com.GoodsShop.model.exceptions.ApiWrongParameterException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserRegisterService {

    UserRepository userRepository;
    PrincipleGroupsRepository principleGroupsRepository;
    BCryptPasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public boolean registerNewBuyer(RegistrationForm registrationForm) {

        if (userRepository.findByEmailIs(registrationForm.getEmail()).isEmpty()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(registrationForm.getEmail());
            userEntity.setUsername(registrationForm.getUsername());
            userEntity.setPassword(passwordEncoder.encode(registrationForm.getPass()));

            PrincipleGroups principleGroups = principleGroupsRepository.findByCodeIs("ROLE_BUYER");
            Set<PrincipleGroups> userRoles = new HashSet<>();
            userRoles.add(principleGroups);
            userEntity.setUserGroups(userRoles);

            userRepository.save(userEntity);

            return true;
        }
        return false;
    }
    public void registerNewSeller(RegistrationForm registrationForm) throws ApiWrongParameterException {
        Optional<UserEntity> userEntity = userRepository.findByEmailIs(registrationForm.getEmail());
        if (userRepository.findByEmailIs(registrationForm.getEmail()).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(registrationForm.getEmail());
            user.setUsername(registrationForm.getUsername());
            user.setPassword(passwordEncoder.encode(registrationForm.getPass()));

            PrincipleGroups roleBuyer = principleGroupsRepository.findByCodeIs("ROLE_BUYER");
            PrincipleGroups roleSeller = principleGroupsRepository.findByCodeIs("ROLE_SELLER");
            Set<PrincipleGroups> userRoles = new HashSet<>();
            userRoles.add(roleBuyer); userRoles.add(roleSeller);
            user.setUserGroups(userRoles);

            userRepository.save(user);

        } else {
            Set<PrincipleGroups> roles = userEntity.get().getUserGroups();
            if (roles.stream().anyMatch(o -> o.getCode().equals("ROLE_SELLER"))) {
                throw new ApiWrongParameterException("User already has role SELLER");
            }
            PrincipleGroups roleSeller = principleGroupsRepository.findByCodeIs("ROLE_SELLER");
            roles.add(roleSeller);
            userEntity.get().setUserGroups(roles);

            userRepository.save(userEntity.get());
        }


    }


}
