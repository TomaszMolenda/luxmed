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

//        return Lists.newArrayList(
//
//        create("", "ReferralId"),
//        create("SelectedDate", "DateOption"),
//        create("", "PayersCount"),
//        create("5", "CityId"),
//        create("", "LanguageId"),
//        create("false", "SearchFirstFree"),
//        create("19-11-2017", "FromDate"),
//        create("19-12-2017", "ToDate"),
//        create("Any", "TimeOption"),
//        create("", "PayerId"),
//        create(storage.getRequestVerificationToken(), "__RequestVerificationToken"),
//        create("false", "IsFFS"));

        DataEntry isFromStartPage = create(filterForm.getIsFromStartPage(), "isFromStartPage");
        DataEntry post = create(filterForm.getPostId(), "postId");
        DataEntry payersCount = create(filterForm.getPayersCount(), "payersCount");
        DataEntry searchFirstFree = create(filterForm.getSearchFirstFree(), "searchFirstFree");
        DataEntry requestVerificationToken = create(storage.getRequestVerificationToken(), "__RequestVerificationToken");
        DataEntry timeOption = create(filterForm.getTimeOption(), "TimeOption");
        DataEntry city = create(filterForm.getCityId(), "CityId");
        DataEntry clinic = create(filterForm.getClinicId(), "ClinicId");
        DataEntry service = create(filterForm.getServiceId(), "ServiceId");
        DataEntry doctor = create(filterForm.getDoctorId(), "DoctorId");
        DataEntry fromDate = create(filterForm.getDateFrom(), "FromDate");
        DataEntry toDate = create(filterForm.getToDate(), "ToDate");
        DataEntry payer = create(filterForm.getPayerId(), "PayerId");
        DataEntry dateFrom = create(filterForm.getDateFrom(), "dateFrom");

        return Lists.newArrayList(isFromStartPage, payersCount, searchFirstFree,
                requestVerificationToken, timeOption, city, clinic, service, doctor,
                fromDate, toDate, payer, post, dateFrom);
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
