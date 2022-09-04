package cz.sii.authentication.repository;

import cz.sii.authentication.entity.AuthenticationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthenticationDataRepository extends JpaRepository<AuthenticationData, Long>
{
	List<AuthenticationData> findByActor(String actor);
	List<AuthenticationData> findByType(String type);
}
