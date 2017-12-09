package pl.tomo.luxmed.storage;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AdvancedDataExtractor {

    private final DoctorExtractor doctorExtractor;


    @Autowired
    AdvancedDataExtractor(DoctorExtractor doctorExtractor) {
        this.doctorExtractor = doctorExtractor;
    }


    List<Doctor> extractDoctors(Document document) {

        return doctorExtractor.extract(document);
    }
}
