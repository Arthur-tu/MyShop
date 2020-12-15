package shop.example.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    private static final String SEQ_NAME = "users_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)

    private Cart cart;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
    private String password;
    private String email;
    private String phone;
    private boolean archive;
}
