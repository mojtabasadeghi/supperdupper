package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.storage.StorageService;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private StorageService storageService;
    private NoteService noteService;
    private UserService userService;

    public  HomeController(StorageService storageService,NoteService noteService,UserService userService)
    {
        this.storageService=storageService;
        this.noteService=noteService;
        this.userService=userService;
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }
    @PostMapping("/note")
    public String insNote(Authentication authentication,  Notes noteFromHome)
    {
        noteService.insNewNote(noteFromHome,userService.getUser(authentication.getName()).getUserId());
        return "redirect:/home";
    }

    @ModelAttribute("notes")
    public List<Notes> allNotes()
    {
        return noteService.loadAllNotes();
    }
    @ModelAttribute("files")
    public List<File> allFiles()
    {
        return storageService.loadAllFile();
    }

    @ModelAttribute("noteFromHome")
    public Notes getNote()
    {
        return new Notes(-1,"","",-1);
    }
}
