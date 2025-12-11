package br.upe.finance.controllers.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.upe.finance.dtos.ReadPayrollDto;
import br.upe.finance.services.PayrollService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping("/payrolls")
    public String listPayrolls(
            @RequestParam(required = false) LocalDate month, 
            Model model) {

        if (month == null) {
            month = LocalDate.now();
        }

        List<ReadPayrollDto> payrolls = payrollService.getAllEmployeesPayroll(month);

        model.addAttribute("payrolls", payrolls);
        model.addAttribute("currentMonth", month);

        return "payrolls/list"; // You need to create this HTML file
    }
}