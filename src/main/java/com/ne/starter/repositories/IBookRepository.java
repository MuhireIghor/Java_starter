package com.ne.starter.repositories;

import com.ne.starter.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface IBookRepository extends JpaRepository<Book, UUID> {
}
