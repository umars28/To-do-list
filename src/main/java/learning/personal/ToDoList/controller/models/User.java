package learning.personal.ToDoList.controller.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"username"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Username tidak boleh kosong")
    private String username;
    @Column(nullable = false)
    @NotNull(message = "Password tidak boleh kosong")
    private String password;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Email tidak boleh kosong")
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @NotNull(message = "Status akun tidak boleh kosong")
    private Boolean isActive;

    private LocalDateTime lastLogin;

    private String profilePictureUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Role tidak boleh kosong")
    private Role role = Role.USER;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;

    public enum Role {
        USER,
        ADMIN
    }
}
