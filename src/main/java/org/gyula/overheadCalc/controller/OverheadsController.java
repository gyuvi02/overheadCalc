package org.gyula.overheadCalc.controller;

import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.*;
import org.gyula.overheadCalc.service.OverheadsRecordingService;
import org.gyula.overheadCalc.service.PdfService;
import org.gyula.overheadCalc.service.UsersService;
import org.gyula.overheadCalc.util.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("me")
public class OverheadsController {

    UsersService usersService;
    OverheadsRecordingService overheadsRecordingService;
    PdfService pdfService;

    @Autowired
    public OverheadsController(UsersService usersService, OverheadsRecordingService overheadsRecordingService, PdfService pdfService) {
        this.usersService = usersService;
        this.overheadsRecordingService = overheadsRecordingService;
        this.pdfService = pdfService;
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        Users loggedUser = usersService.findByUserName(getAuthUserName());
        model.addAttribute("loggedTenant", loggedUser.getTenant());
        log.info("home page called");
        model.addAttribute("myUser", user);
        return "me/home";
    }

    @GetMapping("/recordGas")
    public String recordGas(Model model) {
//        Users myUser = usersService.findByUserName(getAuthUserName());
        List<A_gas_meter> gasList = usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getGas_meters();
        A_gas_meter latestGas = gasList.get(gasList.size() - 1);
        A_gas_meter newGasMeter = new A_gas_meter();
        model.addAttribute("gasMeter", newGasMeter);
        model.addAttribute("latestGasMeter", latestGas);
        model.addAttribute("userName", getAuthUserName());
        log.info("Adding new gas data, calling the form page");
        return "me/recordGas";
    }

    @GetMapping("overheads-error")
    public String overheadsError(@RequestParam("errorList") BindingResult bindingResult, Model model) {
        model.addAttribute("errorList", bindingResult.getAllErrors());
        model.addAttribute("username", getAuthUserName());
        return "me/overheads-error";
    }

    @PostMapping("/saveGasData")
    public String saveGas(@Valid @ModelAttribute("gasMeter")A_gas_meter gasMeter, BindingResult bindingResult, Model model) {
        List<A_gas_meter> gasList = usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getGas_meters();
        A_gas_meter latestGas = gasList.get(gasList.size() - 1);
        if (gasMeter.getGas_meter() < latestGas.getGas_meter()) {
            bindingResult.rejectValue("gas_meter", "error.gas_meter", "The new gas meter value cannot be lower than the previous value");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("meter", "gas");
            overheadsError(bindingResult, model);
            return "me/overheads-error";
        }
        // need to add a new empty flat to the A_gas_meter instance and then add the id
        gasMeter.setA_flat(new A_flat());
        gasMeter.getA_flat().setId(flatId());
        // now I can save the new gas record
        overheadsRecordingService.saveActualGas(gasMeter);
        model.addAttribute("userName", getAuthUserName());
        log.info("Saved the new gas meter " + gasMeter.getGas_meter());
        //use redirect to prevent duplicate submissions
        return "redirect:/me/home";
    }


    @GetMapping("/recordElectricity")
    public String recordElectricity(Model model) {
        Users myUser = usersService.findByUserName(getAuthUserName());
        List<A_electricity_meter> electricityList = myUser.getTenant().getFlats().get(0).getElectricity_meters();
        A_electricity_meter latestElectricity = electricityList.get(electricityList.size() - 1);
        A_electricity_meter newElectricityMeter = new A_electricity_meter();
        model.addAttribute("electricityMeter", newElectricityMeter);
        model.addAttribute("latestElectricityMeter", latestElectricity);
        model.addAttribute("userName", getAuthUserName());
        log.info("Adding new electricity data, calling the form page");
        return "me/recordElectricity";
    }

    @PostMapping("/saveElectricityData")
    public String saveElectricity(@Valid @ModelAttribute("electricityMeter") A_electricity_meter electricityMeter, BindingResult bindingResult, Model model) {
        List<A_electricity_meter> electricityList = usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getElectricity_meters();
        A_electricity_meter latestElectricity = electricityList.get(electricityList.size() - 1);
        if (electricityMeter.getElectricity_meter() < latestElectricity.getElectricity_meter()) {
            bindingResult.rejectValue("electricity_meter", "error.electricity_meter", "The new electricity meter value cannot be lower than the previous value");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("meter", "electricity");
            overheadsError(bindingResult, model);
            return "me/overheads-error";
        }

        // need to add a new empty flat to the A_gas_meter instance and then add the id
        electricityMeter.setA_flat(new A_flat());
        electricityMeter.getA_flat().setId(flatId());
        // now I can save the new gas record
        overheadsRecordingService.saveActualElectricity(electricityMeter);
        model.addAttribute("userName", getAuthUserName());
        log.info("Saved the new gas meter " + electricityMeter.getElectricity_meter());
        //use redirect to prevent duplicate submissions
        return "redirect:/me/home";
    }


    @GetMapping("/recordWater")
    public String recordWater(Model model) {
        Users myUser = usersService.findByUserName(getAuthUserName());
        List<A_water_meter> waterList = myUser.getTenant().getFlats().get(0).getWater_meters();
        A_water_meter latestWater = waterList.get(waterList.size() - 1);
        A_water_meter newWaterMeter = new A_water_meter();
        model.addAttribute("waterMeter", newWaterMeter);
        model.addAttribute("latestWaterMeter", latestWater);
        model.addAttribute("userName", getAuthUserName());
        log.info("Adding new water data, calling the form page");
        return "me/recordWater";
    }

    @PostMapping("/saveWaterData")
    public String saveWater(@Valid @ModelAttribute("electricityMeter") A_water_meter waterMeter, BindingResult bindingResult, Model model) {
        List<A_water_meter> waterList = usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getWater_meters();
        A_water_meter latestWater = waterList.get(waterList.size() - 1);
        if (waterMeter.getWater_meter() < latestWater.getWater_meter()) {
            bindingResult.rejectValue("water_meter", "error.water_meter", "The new water meter value cannot be lower than the previous value");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("meter", "water");
            overheadsError(bindingResult, model);
            return "me/overheads-error";
        }
        // need to add a new empty flat to the A_gas_meter instance and then add the id
        waterMeter.setA_flat(new A_flat());
        waterMeter.getA_flat().setId(flatId());
        // now I can save the new water record
        overheadsRecordingService.saveActualWater(waterMeter);
        model.addAttribute("userName", getAuthUserName());
        log.info("Saved the new water meter " + waterMeter.getWater_meter());
        //use redirect to prevent duplicate submissions
        return "redirect:/me/home";
    }

    @GetMapping("/invoice")
    public String printInvoice(Model model) {
        Invoice invoiceData = new Invoice();
        model.addAttribute("invoiceData", invoiceData.createInvoiceData(usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0)));
        model.addAttribute("userName", getAuthUserName());
        log.info("Invoice data created for the flat: " + invoiceData.getAddress());
        return "/me/invoice";
    }

    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH-mm");
        try {
            Path file = Paths.get(pdfService.generatePdf(flatId()).getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + "Invoice_" + LocalDateTime.now().format(formatter) + ".pdf");
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private int flatId() {
        return usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getId();
    }

}
