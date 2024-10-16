package com.polstat.perpustakaan.endpoint;

import com.example.soap.gen.*;
import com.polstat.perpustakaan.dto.BookDto;
import com.polstat.perpustakaan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class BookEndpoint {
    private static final String NAMESPACE_URI = "http://www.example.com/soap/gen";

    @Autowired
    private BookService bookService;

    // Handle createBookRequest
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createBookRequest")
    @ResponsePayload
    public CreateBookResponse createBook(@RequestPayload CreateBookRequest request) {
        CreateBookResponse response = new CreateBookResponse();

        try {
            // Extract book data from request and build a BookDto
            BookDto bookDto = BookDto.builder()
                    .title(request.getBook().getTitle())
                    .author(request.getBook().getAuthor())
                    .description(request.getBook().getDescription())
                    .build();

            // Save the book in the database
            bookService.createBook(bookDto);

            // Set response result
            response.setResult("Book successfully added");
        } catch (Exception e) {
            // Handle exception and set error result
            response.setResult("Error adding book: " + e.getMessage());
        }

        return response;
    }

    // Handle getBooksRequest
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getAllBooks(@RequestPayload GetBooksRequest request) {
        GetBooksResponse response = new GetBooksResponse();

        try {
            // Fetch all books from the service
            List<BookDto> books = bookService.getBooks();

            // Convert each BookDto to SOAP Book
            List<Book> soapBooks = books.stream()
                    .map(this::convertToSoapBook) // Conversion method
                    .collect(Collectors.toList());

            // Add books to response
            response.getBooks().addAll(soapBooks);

        } catch (Exception e) {
            // Handle exception (optional logging)
            System.err.println("Error fetching books: " + e.getMessage());
        }

        return response;
    }

    // Handle searchBooksRequest
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchBooksRequest")
    @ResponsePayload
    public SearchBooksResponse searchBooks(@RequestPayload SearchBooksRequest request) {
        SearchBooksResponse response = new SearchBooksResponse();

        try {
            List<BookDto> books;

            // Check the search criteria (author and title or free search)
            if (request.getAuthor() != null && request.getTitle() != null) {
                // Search by both author and title
                books = bookService.searchBooks(request.getAuthor(), request.getTitle());
            } else if (request.getSearch() != null) {
                // Free search based on a search string
                books = bookService.searchBooks(request.getSearch());
            } else {
                // If no search criteria, return an empty list or all books
                books = bookService.getBooks();
            }

            // Convert each BookDto to SOAP Book
            List<Book> soapBooks = books.stream()
                    .map(this::convertToSoapBook) // Conversion method
                    .collect(Collectors.toList());

            // Add books to response
            response.getBooks().addAll(soapBooks);

        } catch (Exception e) {
            // Handle exception
            System.err.println("Error searching books: " + e.getMessage());
        }

        return response;
    }

    // Utility method to convert BookDto to SOAP Book object
    private Book convertToSoapBook(BookDto bookDto) {
        Book soapBook = new Book();
        soapBook.setId(bookDto.getId());
        soapBook.setTitle(bookDto.getTitle());
        soapBook.setAuthor(bookDto.getAuthor());
        soapBook.setDescription(bookDto.getDescription());
        return soapBook;
    }
}
