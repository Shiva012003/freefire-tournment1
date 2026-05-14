package com.freeFire.tournment.controller;

import com.freeFire.tournment.model.Team;
import com.freeFire.tournment.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin("*")

public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public void registerTeam(
            Team team,
            HttpServletResponse response
    ) throws Exception {

        teamService.saveTeam(team);

        response.sendRedirect("/payment.html");
    }

    @GetMapping
    public List<Team> getAllTeams() {

        return teamService.getAllTeams();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadScreenshot(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String uploadDir =
                System.getProperty("user.dir")
                        + "/uploads/";

        File directory = new File(uploadDir);

        if (!directory.exists()) {

            directory.mkdirs();
        }

        String filePath =
                uploadDir
                        + file.getOriginalFilename();

        file.transferTo(new File(filePath));

        return ResponseEntity.ok(
                "Screenshot Uploaded Successfully"
        );
    }
}