//package br.com.fiap.epictask.task;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/superpoder")
//public class SuperpoderController {
//
//
//        private final SuperpoderService superpoderService;
//
//        public SuperpoderController(SuperpoderService superpoderService){
//            this.superpoderService = superpoderService;
//        }
//
//        @GetMapping
//        public String index(Model model){
//            System.out.println("Mostrando a página de SuperPoderes");
//            List<Superpoder> lista = superpoderService.getAllSuperpoderes();
//            System.out.println("Superpoderes encontrados: " + lista.size());
//            model.addAttribute("superpoderes", superpoderService.getAllSuperpoderes());
//            return "index";
//        }
//
//}
