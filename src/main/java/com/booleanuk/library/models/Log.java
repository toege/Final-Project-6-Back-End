package com.booleanuk.library.models;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "logDat")
    private LocalDateTime logDat;

//    @OneToMany(mappedBy = "log", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIncludeProperties(value = {"id", "notes"})
//    private List<WorkoutPlan> workoutPlans;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonIncludeProperties(value = {"id", "username", "email"})
//    private User user;

}
