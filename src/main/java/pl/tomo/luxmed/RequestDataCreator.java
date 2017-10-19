package pl.tomo.luxmed;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.DataEntry;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
class RequestDataCreator {

    public List<DataEntry> create(FirstStepFilterForm firstStepFilterForm) {

        DataEntry city = create(firstStepFilterForm.getCityId(), "cityId");
        DataEntry post = create(firstStepFilterForm.getPostId(), "postId");
        DataEntry service = create(firstStepFilterForm.getServiceId(), "serviceId");
        DataEntry dateFrom = create(firstStepFilterForm.getDateFrom().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "dateFrom");
        DataEntry doctor = create(firstStepFilterForm.getDoctorId(), "doctorId");
        DataEntry payer = create(firstStepFilterForm.getPayerId(), "payerId");
        DataEntry payersCount = create(firstStepFilterForm.getPayersCount(), "payersCount");

        return Lists.newArrayList(city, post, service, dateFrom, doctor, payer, payersCount);
    }

    private DataEntry create(String cityId, String fieldName) {

        DataEntry dataEntry = new DataEntry(fieldName, cityId);

        if (cityId == null) {

            dataEntry.setValue(EMPTY);
        }
        return dataEntry;
    }
}
