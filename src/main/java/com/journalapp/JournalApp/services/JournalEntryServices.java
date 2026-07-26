package com.journalapp.JournalApp.services;

import com.journalapp.JournalApp.entity.JournalEntry;
import com.journalapp.JournalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepository.save(journalEntry);
    }
}


//controler-- service-- repository