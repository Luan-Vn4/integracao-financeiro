package br.upe.finance.controllers.view;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.upe.finance.dtos.ReadSalaryDto;
import br.upe.finance.dtos.SalaryRequestDto;
import br.upe.finance.services.SalaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("/salaries")
    public String listSalaries(
        @RequestParam(defaultValue = "0")
        int page,
        @RequestParam(defaultValue = "10")
        int size,
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

        return "salaries/list";
    }

    @GetMapping("/salaries/new")
    public String showCreateForm(Model model) {
        model.addAttribute(
            "salaryRequest",
            new SalaryRequestDto(null, null, null)
        );
        return "salaries/create";
    }

    @PostMapping("/salaries")
    public String createSalary(
        @Valid
        @ModelAttribute("salaryRequest")
        SalaryRequestDto salaryRequest,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes,
        Model model) {

        if (bindingResult.hasErrors()) {
            return "salaries/create";
        }

        try {
            salaryService.registerSalary(salaryRequest);
            redirectAttributes.addFlashAttribute(
                "successMessage",
                "Salário registrado com sucesso para o funcionário ID: "
                    + salaryRequest.employeeId()
            );
            return "redirect:/salaries";
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue(
                "employeeId",
                "error.salaryRequest",
                "Já existe um salário registrado para este funcionário."
            );
            return "salaries/create";
        } catch (Exception e) {
            bindingResult.reject(
                "error.global",
                "Erro ao registrar salário: " + e.getMessage()
            );
            return "salaries/create";
        }
    }
}