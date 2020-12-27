package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO notes (notetitle, notedescription, fk_userid) VALUES" +
                              "(#{notetitle}, #{notedescription}, #{fk_userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote(Notes note);

    @Select("SELECT * FROM notes")
    List<Notes> getAllNotes();
}
