package com.business.report.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.business.report.model.UserPermission;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
    boolean existsByUserId(String userId);
}
