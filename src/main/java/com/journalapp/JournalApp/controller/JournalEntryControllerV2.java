package com.journalapp.JournalApp.controller;

import com.journalapp.JournalApp.entity.JournalEntry;
import com.journalapp.JournalApp.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal/v2")
public class JournalEntryControllerV2 {

    @Autowired
    public JournalEntryServices journalEntryServices;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryServices.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryServices.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryServices.getById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryServices.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        myEntry.setId(id);
        journalEntryServices.saveEntry(myEntry);
        return myEntry;
    }
}
