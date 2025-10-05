package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LibraryManagementSystemMain {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library_management_system");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Author author = new Author();
        author.setAuthorName("Sabahattin Ali");
        author.setBirthDate(LocalDate.of( 1907,2,25));
        author.setCountry("Türkiye");

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Martı Yayınevi");
        publisher.setEstablishmentYear(1950);
        publisher.setAddress("Istanbul");

        Category category = new Category();
        category.setCategoryName("Roman");
        category.setDescription("Kurgu");

        Category category2 = new Category();
        category2.setCategoryName("Hikaye");
        category2.setDescription("Kurgu");

        Book book = new Book();
        book.setBookName("Kuyucaklı Yusuf");
        book.setAuthor(author);
        book.setPublicationYear(1937);
        book.setStock(1002);
        book.setPublisher(publisher);

        List<Category> categoryList = Arrays.asList(category,category2);
        book.setCategories(categoryList);
        category.setBooks(Arrays.asList(book));
        category2.setBooks(Arrays.asList(book));

        BookBorrowing borrow = new BookBorrowing();
        borrow.setBorrowerName("Bekir Ahmetli");
        borrow.setBorrowingDate(LocalDate.now());
        borrow.setReturnDate(null);
        borrow.setBook(book);

        book.setBookBorrowings(Arrays.asList(borrow));

        entityManager.persist(author);
        entityManager.persist(book);
        entityManager.persist(borrow);
        entityManager.persist(category);
        entityManager.persist(category2);
        entityManager.persist(publisher);


        transaction.commit();

        entityManagerFactory.close();
        entityManager.close();
    }
}
