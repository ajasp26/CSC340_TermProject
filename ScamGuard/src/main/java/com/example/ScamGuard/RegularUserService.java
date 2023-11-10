package com.example.ScamGuard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegularUserService {

    private final ScamReportRepository scamReportRepository;

    @Autowired
    public RegularUserService(ScamReportRepository scamReportRepository) {
        this.scamReportRepository = scamReportRepository;
    }

    // Method to submit a scam report
    public ScamReport submitScamReport(ScamReport scamReport) {
        // Logic to save scam report to the database
        return scamReportRepository.save(scamReport);
    }

    // Method to view scam reports
    public List<ScamReport> viewScamReports() {
        // Logic to retrieve scam reports from the database
        return scamReportRepository.findAll();
    }
}