package com.journalapp.JournalApp.controller;

import com.journalapp.JournalApp.entity.JournalEntry;
import com.journalapp.JournalApp.services.JournalEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal/v2")
public class JournalEntryControllerV2 {


    @Autowired
    public  JournalEntryServices journalEntryServices;

    public Map<String, JournalEntry> JournalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(JournalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryServices.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable String myId) {
        return JournalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable String myId) {
        return JournalEntries.remove(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable String id, @RequestBody JournalEntry myEntry) {
        return JournalEntries.put(id, myEntry);
    }
}
