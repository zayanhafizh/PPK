package com.polstat.perpustakaan.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.polstat.perpustakaan.dto.BookDto;
import com.polstat.perpustakaan.rpc.JsonRpcRequest;
import com.polstat.perpustakaan.rpc.JsonRpcResponse;
import com.polstat.perpustakaan.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonRpcController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Handle JSON-RPC requests",
            description = "Supports methods: createBook, getBooks, and searchBooks.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful JSON-RPC response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JsonRpcResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request format or missing parameters",
                    content = @Content)
    })
    @PostMapping("/jsonrpc")
    public ResponseEntity<Object> handleJsonRpcRequest(@RequestBody JsonRpcRequest request) {
        try {
            String method = request.getMethod();
            JsonNode params = request.getParams();
            System.out.println("Method: " + method);

            switch (method) {
                case "createBook":
                    String title = params.get("title").asText();
                    String author = params.get("author").asText();
                    String description = params.get("description").asText();
                    BookDto book = BookDto.builder()
                            .title(title)
                            .description(description)
                            .author(author)
                            .build();
                    bookService.createBook(book);
                    return ResponseEntity.ok(new JsonRpcResponse("created", request.getId()));

                case "getBooks":
                    List<BookDto> books = bookService.getBooks();
                    return ResponseEntity.ok(new JsonRpcResponse(books, request.getId()));

                case "searchBooks":
                    if (params.has("author") && params.has("title")) {
                        author = params.get("author").asText();
                        title = params.get("title").asText();
                        List<BookDto> searchResults = bookService.searchBooks(author, title);
                        return ResponseEntity.ok(new JsonRpcResponse(searchResults, request.getId()));
                    } else if (params.has("search")) {
                        String search = params.get("search").asText();
                        List<BookDto> searchResults = bookService.searchBooks(search);
                        return ResponseEntity.ok(new JsonRpcResponse(searchResults, request.getId()));
                    }
                    // Fall-through if no valid search parameters are present

                default:
                    return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
