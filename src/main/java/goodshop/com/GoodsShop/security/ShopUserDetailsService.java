package goodshop.com.GoodsShop.security;

import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserEntity;
import goodshop.com.GoodsShop.model.entities.RolesPermissions.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByEmailIs(username);
        if(user.isPresent()) {
            return new ShopUserDetails(user.get());
        }
        throw  new UsernameNotFoundException("user not found!");
    }


}
