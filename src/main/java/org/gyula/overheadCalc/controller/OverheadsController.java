package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.*;
import org.gyula.overheadCalc.service.GasService;
import org.gyula.overheadCalc.service.OverheadsRecordingService;
import org.gyula.overheadCalc.service.UsersService;
import org.gyula.overheadCalc.util.CurrentData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("me")
public class OverheadsController {

    UsersService usersService;
    OverheadsRecordingService overheadsRecordingService;
//    GasService gasService;
//    GasService gasService;

    public OverheadsController(UsersService usersService, OverheadsRecordingService overheadsRecordingService) {
        this.usersService = usersService;
        this.overheadsRecordingService = overheadsRecordingService;
//        this.gasService = gasService;
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        Users loggedUser = usersService.findByUserName(getAuthUserName());
        model.addAttribute("loggedTenant", loggedUser.getTenant());
        log.info("home page called");
//        A_gas_meter newGas = new A_gas_meter(110000);
//        gasService.save(newGas);
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

    @PostMapping("/saveGasData")
    public String saveGas(@ModelAttribute("gasMeter") A_gas_meter gasMeter, Model model) {
        // need to add the original flat id in a complicated way, first get the id, as I do not put it into the model
//        int flatId = usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getId();
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
    public String saveElectricity(@ModelAttribute("electricityMeter") A_electricity_meter electricityMeter, Model model) {
        // need to add the original flat id in a complicated way, first get the id, as I do not put it into the model
//        int flatId = usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getId();
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
    public String saveWater(@ModelAttribute("electricityMeter") A_water_meter waterMeter, Model model) {
        // need to add the original flat id in a complicated way, first get the id, as I do not put it into the model
//        int flatId = usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getId();
        // need to add a new empty flat to the A_gas_meter instance and then add the id
        waterMeter.setA_flat(new A_flat());
        waterMeter.getA_flat().setId(flatId());
        // now I can save the new gas record
        overheadsRecordingService.saveActualWater(waterMeter);
        model.addAttribute("userName", getAuthUserName());
        log.info("Saved the new water meter " + waterMeter.getWater_meter());
        //use redirect to prevent duplicate submissions
        return "redirect:/me/home";
    }


//    @GetMapping("/addOverheadsData")
//    public String addOverheadsData(Model model) {
//        Users myUser = usersService.findByUserName(getAuthUserName());
//        CurrentData currentData = new CurrentData();
//        currentData.createData(myUser.getTenant().getFlats().get(0));
//        model.addAttribute("userName", getAuthUserName());
////        model.addAttribute("myUser", myUser);
//        model.addAttribute("overheadsData", currentData);
////        model.addAttribute("tenantList", tenantService.findAll());
//        log.info("Adding new overheads data, calling the form page");
//        return "me/addOverheads-form";
//    }

    private String getAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private int flatId() {
        return usersService.findByUserName(getAuthUserName()).getTenant().getFlats().get(0).getId();
    }


}
