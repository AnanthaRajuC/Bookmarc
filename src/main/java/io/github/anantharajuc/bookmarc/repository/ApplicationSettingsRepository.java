package io.github.anantharajuc.bookmarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.bookmarc.model.ApplicationSettings;

/**
 * Repository class for <code>ApplicationSetings</code> domain object. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Repository
public interface ApplicationSettingsRepository extends JpaRepository<ApplicationSettings, Long>
{

}