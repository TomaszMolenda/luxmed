package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AdvancedDataExtractor {

    private final DoctorExtractor doctorExtractor;
    private final PayerExtractor payerExtractor;


    @Autowired
    AdvancedDataExtractor(DoctorExtractor doctorExtractor,
                          PayerExtractor payerExtractor) {
        this.doctorExtractor = doctorExtractor;
        this.payerExtractor = payerExtractor;
    }


    List<Doctor> extractDoctors(Document document) {

        return doctorExtractor.extract(document);
    }


    List<Payer> extractPayers(Document document) {

        return payerExtractor.extract(document);
    }
}
