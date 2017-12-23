package pl.tomo.luxmed.payers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.Redirect;
import pl.tomo.luxmed.storage.Storage;

import static pl.tomo.luxmed.storage.Log.log;

@Controller
@RequestMapping("payers")
class PayerController {

    private final Storage storage;
    private final PayerFetcher payerFetcher;
    private final PayerQueries payerQueries;

    @Autowired
    PayerController(Storage storage, PayerFetcher payerFetcher, PayerQueries payerQueries) {
        this.storage = storage;
        this.payerFetcher = payerFetcher;
        this.payerQueries = payerQueries;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("payers", payerFetcher.fetch());

        return "payers";
    }

    @PostMapping
    private String post(@ModelAttribute("payerId") String payerId) {

        storage.addLog(log("Choose payer, id:" + payerId + ", name: " + payerQueries.getData(payerId)));

        storage.setPayerId(payerId);

        return Redirect.builder()
                .path("/summary")
                .build();


    }
}
