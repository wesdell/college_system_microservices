package com.wesdell.courses.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wesdell.courses.interfaces.Modality;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Min(value = 10, message = "Capacity must be at least 10")
    @Max(value = 40, message = "Capacity cannot exceed 40")
    private int capacity;

    @NotNull(message = "Modality is required")
    @Enumerated(EnumType.STRING)
    private Modality modality;

    private Long subjectId;

    private Long professorId;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference("course-classroom")
//    private Set<Classroom> classrooms = new HashSet<>();
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference("course-enrollment")
//    private Set<Enrollment> enrollments = new HashSet<>();

    @AssertTrue(message = "End date must be after or equal to start date")
    private boolean isEndDateValid() {
        return startDate != null && endDate != null && !endDate.isBefore(startDate);
    }

}
