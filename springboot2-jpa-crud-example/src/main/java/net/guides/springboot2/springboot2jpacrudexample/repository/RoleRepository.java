package net.guides.springboot2.springboot2jpacrudexample.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot2.springboot2jpacrudexample.model.ERole;
import net.guides.springboot2.springboot2jpacrudexample.model.Role;

/*import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;*/

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
