package br.upe.finance.controllers.view;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            @RequestParam(required = false) String month,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        LocalDate monthDate;
        if (month == null || month.isEmpty()) {
            monthDate = LocalDate.now();
        } else {
            try {
                // Parse "yyyy-MM" format and use first day of month
                YearMonth yearMonth = YearMonth.parse(month);
                monthDate = yearMonth.atDay(1);
            } catch (DateTimeParseException e) {
                // Fallback to current month if parsing fails
                monthDate = LocalDate.now();
            }
        }

        if (size != 10 && size != 25 && size != 50 && size != 100) {
            size = 10;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "paymentDate"));
        Page<ReadPayrollDto> payrollPage = payrollService.getAllEmployeesPayroll(monthDate, pageable);

        model.addAttribute("payrollPage", payrollPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", payrollPage.getTotalPages());
        model.addAttribute("totalElements", payrollPage.getTotalElements());
        model.addAttribute("currentMonth", monthDate);

        return "payrolls/list";
    }
}