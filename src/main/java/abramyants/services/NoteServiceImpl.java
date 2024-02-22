package abramyants.services;

import abramyants.models.Note;
import abramyants.repositories.NoteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    //Request notes from db by id
    @Override
    public Note getById(long id) {
        return noteRepository.findById(id).orElse(null);
    }

    //Request notes from db
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    //Creating note in db
    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    //Update note in db
    @Override
    public Optional<Note> editNote(Long id, Note note) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            note.setId(id);
            return Optional.of(noteRepository.save(note));
        } else {
            return Optional.empty();
        }
    }

    //Deleting notes from db
    @Override
    public void delete(long id) {
        noteRepository.deleteById(id);
    }
}