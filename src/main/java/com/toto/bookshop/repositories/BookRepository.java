package com.toto.bookshop.repositories;

import com.toto.bookshop.entities.Book;
import com.toto.bookshop.entities.Publisher;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByPublisher(Publisher publisher);

}