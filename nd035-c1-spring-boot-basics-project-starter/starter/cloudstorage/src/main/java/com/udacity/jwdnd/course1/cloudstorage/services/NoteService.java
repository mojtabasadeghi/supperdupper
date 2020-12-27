package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;


    public NoteService(NoteMapper noteMapper) {
        this.noteMapper=noteMapper;
    }

    public void insNewNote(Notes notes, int user_id)
    {
        notes.setFk_userid(user_id);
        noteMapper.insertNote(notes);
    }

    public List<Notes> loadAllNotes ()
    {
        return noteMapper.getAllNotes();
    }
}
