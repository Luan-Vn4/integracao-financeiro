package br.upe.finance.controllers.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.upe.finance.dtos.ReadResourceManagementDto;
import br.upe.finance.services.ResourceManagementService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ResourceManagementController {

    private final ResourceManagementService resourceManagementService;

    @GetMapping("/resource-management")
    public String listResourceManagement(
        @RequestParam(defaultValue = "0")
        int page,
        @RequestParam(defaultValue = "10")
        int size,
        Model model) {

        // Validate page size (only allow 10, 25, 50, 100)
        if (size != 10 && size != 25 && size != 50 && size != 100) {
            size = 10;
        }

        Pageable pageable = PageRequest
            .of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ReadResourceManagementDto> resourceManagementPage = resourceManagementService
            .findAll(pageable);

        model.addAttribute("resourceManagementPage", resourceManagementPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model
            .addAttribute("totalPages", resourceManagementPage.getTotalPages());
        model.addAttribute(
            "totalElements",
            resourceManagementPage.getTotalElements()
        );

        return "resource-management/list";
    }
}
