package goodshop.com.GoodsShop.model.entities.RolesPermissions;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "principle_groups")
public class PrincipleGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    private String name;
    @ManyToMany(mappedBy = "userGroups")
    private Set<UserEntity> users;

}
