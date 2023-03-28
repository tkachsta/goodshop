package goodshop.com.GoodsShop.model.dto.register;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationForm {

    private String email;
    private String username;
    private String pass;



}
