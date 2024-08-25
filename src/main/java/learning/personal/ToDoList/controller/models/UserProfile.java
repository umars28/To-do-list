package learning.personal.ToDoList.controller.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Nama tidak boleh kosong")
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @NotNull(message = "Birth date todak boleh kosong")
    @Past(message = "Tanggal lahir harus dibawah tanggal sekarang")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @NotNull(message = "No. HP tidak boleh kosong")
    private String phoneNumber;
    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String websiteUrl;

    @Column(columnDefinition = "JSON")
    private String socialMediaLinks;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    public enum Gender {
        MALE,
        FEMALE
    }

}
