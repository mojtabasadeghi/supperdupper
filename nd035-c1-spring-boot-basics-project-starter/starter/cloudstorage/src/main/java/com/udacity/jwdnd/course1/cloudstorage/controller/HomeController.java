package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.storage.StorageService;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private StorageService storageService;
    private NoteService noteService;
    private UserService userService;
    private CredentialsService credentialsService;


    public  HomeController(StorageService storageService,NoteService noteService,UserService userService,CredentialsService credentialsService)
    {
        this.storageService=storageService;
        this.noteService=noteService;
        this.userService=userService;
        this.credentialsService=credentialsService;
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }
    @PostMapping("/note")
    public String insOrPpdNote(Authentication authentication,  Notes noteFromHome)
    {
        try {
            if(Integer.parseInt(noteFromHome.getNoteinsorupd()) >0)
                noteService.updateNote(noteFromHome,userService.getUser(authentication.getName()).getUserId(),Integer.parseInt(noteFromHome.getNoteinsorupd()));
            else
                noteService.insNewNote(noteFromHome,userService.getUser(authentication.getName()).getUserId());

            return "redirect:/home";
        }
        catch (Exception e)
        {
            System.out.println(e.getCause());
            return "redirect:/home";
        }

    }

    @ModelAttribute("credentialsList")
    public List<Credentials> allCredentials(Authentication authentication)
    {
        return credentialsService.getAllCredentials(authentication.getName());
    }

    @ModelAttribute("noteslist")
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
    public Notes setformnodes()
    {
        return new Notes();
    }





    @GetMapping("/note/delete/{id}")
    public String deleteFile(@PathVariable int id,Model model) {

        noteService.delNote(id);
        return "redirect:/home";
    }




}
