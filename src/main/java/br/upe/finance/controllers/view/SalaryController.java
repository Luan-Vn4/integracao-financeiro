package br.upe.finance.controllers.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.upe.finance.dtos.ReadSalaryDto;
import br.upe.finance.services.SalaryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("/salaries")
    public String listSalaries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        if (size != 10 && size != 25 && size != 50 && size != 100) {
            size = 10;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("employeeId"));
        
        Page<ReadSalaryDto> salaryPage = salaryService.findAll(pageable);

        model.addAttribute("salaryPage", salaryPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", salaryPage.getTotalPages());
        model.addAttribute("totalElements", salaryPage.getTotalElements());

        return "salaries/list"; // You need to create this HTML file
    }
}