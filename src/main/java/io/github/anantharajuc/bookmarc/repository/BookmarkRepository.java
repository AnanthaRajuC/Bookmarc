package io.github.anantharajuc.bookmarc.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.bookmarc.model.Bookmark;

/**
 * Repository class for <code>Bookmark</code> domain object. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer>
{
	@Query(value = "select * from BOOKMARK where ADD_DATE > cast(CURRENT_DATE() - CAST(:duration AS INTEGER) as date)", nativeQuery = true)
	public List<Bookmark> getAllBetweenDates(@Param("duration") Integer duration);
	
	@Query(value = "select distinct host from BOOKMARK where WEBSITE_CATEGORY is null", nativeQuery = true)
	public List<Bookmark> getUnclassifiedBookmarks();
	
	@Query(value = "UPDATE BOOKMARK A SET WEBSITE_CATEGORY = (SELECT WEBSITE_CATEGORY FROM HOST_CLASSIFICATION B WHERE B.HOST = A.HOST) WHERE EXISTS (SELECT WEBSITE_CATEGORY FROM HOST_CLASSIFICATION B WHERE B.HOST = A.HOST)", nativeQuery = true)
	public List<Bookmark> updateWebsiteCategory();
	
	@Transactional(readOnly = true)
	Boolean existsByUrl(@NotBlank String url);
}
