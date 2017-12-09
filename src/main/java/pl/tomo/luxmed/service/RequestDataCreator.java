package pl.tomo.luxmed.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.DataEntry;
import pl.tomo.luxmed.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class RequestDataCreator {

    private final Storage storage;

    @Autowired
    RequestDataCreator(Storage storage) {
        this.storage = storage;
    }

    public List<DataEntry> create(FilterForm filterForm) {

        return Lists.newArrayList(

        create(filterForm.getIsFromStartPage(), "isFromStartPage"),
        create(filterForm.getPostId(), "postId"),
        create(filterForm.getPayersCount(), "payersCount"),
        create(filterForm.getSearchFirstFree(), "searchFirstFree"),
        create(storage.getRequestVerificationToken(), "__RequestVerificationToken"),
        create(filterForm.getTimeOption(), "TimeOption"),
        create(filterForm.getCityId(), "CityId"),
        create(filterForm.getClinicId(), "ClinicId"),
        create(filterForm.getServiceId(), "ServiceId"),
        create(filterForm.getDoctorId(), "DoctorId"),
        create(filterForm.getDateFrom(), "FromDate"),
        create(filterForm.getToDate(), "ToDate"),
        create(filterForm.getPayerId(), "PayerId"),
        create(filterForm.getDateFrom(), "dateFrom")

        );
    }

    private DataEntry create(LocalDate dateFrom, String fieldName) {

        if (dateFrom == null) {

            return create(EMPTY, fieldName);
        }

        return create(dateFrom.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), fieldName);
    }

    private DataEntry create(String value, String fieldName) {

        DataEntry dataEntry = new DataEntry(fieldName, value);

        if (isEmpty(value)) {

            dataEntry.setValue(EMPTY);
        }
        return dataEntry;
    }
}
