package com.example.security.source.entity;

import com.example.security.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private Long userId;
    private String roleName;
    private String description;
    private Boolean isActive;
    private Boolean isBuildIn;

}
