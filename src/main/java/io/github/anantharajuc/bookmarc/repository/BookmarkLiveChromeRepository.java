package io.github.anantharajuc.bookmarc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.bookmarc.model.BookmarkLiveChrome;

@Repository
public interface BookmarkLiveChromeRepository extends JpaRepository<BookmarkLiveChrome, Integer>
{

}
