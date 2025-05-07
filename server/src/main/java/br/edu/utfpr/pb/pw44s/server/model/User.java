package br.edu.utfpr.pb.pw44s.server.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "cadUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4)
    private String name;

    @NotNull
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{br.edu.utfpr.pb.pw44s.server.senha}")
    private String password;

    @NotNull
    @Size(min = 4)
    private String email;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return AuthorityUtils.createAuthorityList("ROLE_USER");
//    }
//
//    @Override
//    @Transient
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isEnabled() {
//        return true;
//    }

}