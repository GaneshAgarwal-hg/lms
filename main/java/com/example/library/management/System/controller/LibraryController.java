package com.example.library.management.System.controller;

        import com.example.library.management.System.exception.ResourceNotFoundException;
        import com.example.library.management.System.model.Borrow;
        import com.example.library.management.System.model.Request;
        import com.example.library.management.System.repository.BorrowRepository;
        import com.example.library.management.System.repository.LibraryRepository;
        import com.example.library.management.System.repository.RequestRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import com.example.library.management.System.model.Library;
        import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class LibraryController {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private RequestRepository requestRepository;

    // get all details
    @GetMapping("/library")
    public List<Library> getAllBooks() {
        return libraryRepository.findAll();
    }

    @DeleteMapping("/library/{id}")
    public ResponseEntity<HttpStatus> deleteLibrary(@PathVariable int id) {
        Library library = libraryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not exist with id" + id));
        libraryRepository.delete(library);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/library/borrow")
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @GetMapping("/library/borrow/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable int id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not exist with id" + id));
        return ResponseEntity.ok(borrow);
    }

    @DeleteMapping("/library/borrow/{id}")
    public ResponseEntity<HttpStatus> deleteBorrow(@PathVariable int id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not exist with id" + id));
        borrowRepository.delete(borrow);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/library/borrow")
    public Borrow borrowLibrary(@RequestBody Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @GetMapping("/library/request")
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @GetMapping("/library/request/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable int id) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not exist with id" + id));
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/library/request/{id}")
    public ResponseEntity<HttpStatus> deleteRequest(@PathVariable int id) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not exist with id" + id));
        requestRepository.delete(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/library/request")
    public Request requestLibrary(@RequestBody Request request) {
        return requestRepository.save(request);
    }


    @PostMapping("/library")
    public Library createLibrary(@RequestBody Library library) {
        return libraryRepository.save(library);
    }


    @GetMapping("/library/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable int id) {
        Library library = libraryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not exist with id" + id));
        return ResponseEntity.ok(library);
    }

    @PutMapping("/library/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable int id, @RequestBody Library libraryDetails) {
        Library updateLibrary = libraryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not exist with id" + id));
        updateLibrary.setBookName(libraryDetails.getBookName());
        updateLibrary.setAuthorName(libraryDetails.getAuthorName());
        updateLibrary.setRating(libraryDetails.getRating());
        updateLibrary.setImage(libraryDetails.getImage());
        libraryRepository.save(updateLibrary);
        return ResponseEntity.ok(updateLibrary);
    }
}
