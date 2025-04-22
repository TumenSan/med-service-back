package com.example.demo.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Table (name = "models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Model {
    //model_id
    //name
    //version
    //path
    //trained_at

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long id;

    @Column(name = "model_name", unique = true, nullable = false)
    private String modelName;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String path;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
