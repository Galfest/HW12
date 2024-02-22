package abramyants.controllers;

import abramyants.models.Note;
import abramyants.services.FileGateway;
import abramyants.services.NoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Рест контроллер
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteServiceImpl noteService;

    private final FileGateway fileGateway;

    //Adding note .txt
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        fileGateway.writeToFile(note.getTitle() + ".txt", note.toString());
        return new ResponseEntity<>(noteService.create(note), HttpStatus.CREATED);
    }

    //Get all notes by id
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") long id) {
        return new ResponseEntity<>(noteService.getById(id), HttpStatus.OK);
    }

    //Get all notes
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    //Udating note
    @PutMapping("/{id}")
    public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody Note note) {
        Optional<Note> updatedNote = noteService.editNote(id, note);
        return updatedNote.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete note by id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") long id) {
        noteService.delete(id);
        return ResponseEntity.ok().build();
    }
}