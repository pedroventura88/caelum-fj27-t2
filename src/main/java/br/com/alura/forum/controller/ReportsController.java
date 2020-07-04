package br.com.alura.forum.controller;

import br.com.alura.forum.repository.OpenTopicsByCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/admin/reports")
@Controller
public class ReportsController {

    private OpenTopicsByCategoryRepository openTopicsByCategoryRepository;

    @GetMapping("/open-topics-by-category")
    public String showOpenTopicsByCategoryReport(Model model) {
        model.addAttribute("openTopics",	openTopicsByCategoryRepository.findAllByCurrentMonth());
        return "report";
    }

}
