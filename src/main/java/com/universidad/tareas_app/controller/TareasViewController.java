package com.universidad.tareas_app.controller;

import com.universidad.tareas_app.service.TareaService;
import com.universidad.tareas_app.repository.TareaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TareasViewController {

    private final TareaRepository repo;

    public TareasViewController(TareaRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/tareas")
    public String listarTareas(Model model) {
        model.addAttribute("tareas", repo.findAll());
        return "tareas";
    }
}