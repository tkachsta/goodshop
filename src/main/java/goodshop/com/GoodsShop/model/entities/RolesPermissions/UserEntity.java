package goodshop.com.GoodsShop.model.entities.RolesPermissions;
import goodshop.com.GoodsShop.model.entities.Rating.RatingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Long balance;
    @Column(name = "account_verified")
    private boolean accountVerified;
    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts;
    @Column(name = "login_disabled")
    private boolean loginDisabled;
    @OneToMany(mappedBy = "user")
    private Set<RatingEntity> goodsRating;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_group")
    )
    private Set<PrincipleGroups> userGroups = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && username.equals(that.username) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }

}
