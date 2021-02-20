package com.jovcevski.librio.web.repository;

import com.jovcevski.librio.web.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> { }
