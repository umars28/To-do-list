package learning.personal.ToDoList.controller.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "title tidak boleh kosong")
    private String title;

    private String description;

    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "status tidak boleh kosong")
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Past(message = "Completed at harus dibawah tanggal sekarang")
    private LocalDateTime completedAt;
    private Boolean isRecurring;
    private RecurringInterval recurringInterval;
    private BigDecimal estimatedTimeHours;
    private BigDecimal actualTimeHours;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "tasks_categories",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        ARCHIVED
    }

    public enum RecurringInterval {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

}
