package com.journalapp.JournalApp.controller;

import com.journalapp.JournalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    public Map<Long , JournalEntry> JournalEntries= new HashMap<>();


    @GetMapping
    public List<JournalEntry> getAll()
    {
      return  new ArrayList<>(JournalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry)
    {
        JournalEntries.put(myEntry.getId(),myEntry);
        return true;

    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable long myId )
    {
        return  JournalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable long myId )
    {
        return  JournalEntries.remove(myId);
    }

    @PutMapping("/id/{id}")
    public  JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry)
    {
      return   JournalEntries.put(id ,myEntry);

    }


}
