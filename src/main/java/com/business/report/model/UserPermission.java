package com.business.report.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_permissions")
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId; // ID OAuth2.

    @Column(name = "has_permission", nullable = false)
    private boolean hasPermission; // permisos.

    @Column(name = "role", nullable = false)
    private String role; // Rol

    @Column(name = "oauth_token", nullable = false)
    private String oauthToken; // Token de acceso OAuth2.
}
