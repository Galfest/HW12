package abramyants.services;

import abramyants.models.Note;

import java.util.List;
import java.util.Optional;

//Для маштабируемости был выбран паттерн Service Layer.
//Разделил логику и работу с данными

public interface NoteService {
    List<Note> getAllNotes();
    Note getById(long id);
    Note create(Note note);
    Optional<Note> editNote(Long id, Note note);
    void delete(long id);
}