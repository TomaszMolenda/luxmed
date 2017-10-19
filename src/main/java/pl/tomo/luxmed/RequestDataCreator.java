package pl.tomo.luxmed;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.tomo.luxmed.connection.DataEntry;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
class RequestDataCreator {

    public List<DataEntry> create(FilterForm filterForm) {

        DataEntry city = create(filterForm.getCityId(), "cityId");
        DataEntry post = create(filterForm.getPostId(), "postId");
        DataEntry service = create(filterForm.getServiceId(), "serviceId");
        DataEntry dateFrom = create(filterForm.getDateFrom().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "dateFrom");
        DataEntry doctor = create(filterForm.getDoctorId(), "doctorId");
        DataEntry payer = create(filterForm.getPayerId(), "payerId");
        DataEntry payersCount = create(filterForm.getPayersCount(), "payersCount");

        return Lists.newArrayList(city, post, service, dateFrom, doctor, payer, payersCount);
    }

    public List<DataEntry> create2(FilterForm filterForm) {

        DataEntry isFromStartPage = create(filterForm.getIsFromStartPage(), "IsFromStartPage");
        DataEntry payersCount = create(filterForm.getPayersCount(), "PayersCount");
        DataEntry searchFirstFree = create(filterForm.getSearchFirstFree(), "SearchFirstFree");
        DataEntry requestVerificationToken = create(filterForm.getRequestVerificationToken(), "__RequestVerificationToken");
        DataEntry timeOption = create(filterForm.getTimeOption(), "TimeOption");
        DataEntry city = create(filterForm.getCityId(), "CityId");
        DataEntry clinic = create(filterForm.getClinicId(), "ClinicId");
        DataEntry service = create(filterForm.getServiceId(), "ServiceId");
        DataEntry doctor = create(filterForm.getDoctorId(), "DoctorId");
        DataEntry fromDate = create(filterForm.getDateFrom().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "FromDate");
        DataEntry toDate = create(filterForm.getToDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), "ToDate");
        DataEntry payer = create(filterForm.getPayerId(), "PayerId");

        return Lists.newArrayList(isFromStartPage, payersCount, searchFirstFree,
                requestVerificationToken, timeOption, city, clinic, service, doctor,
                fromDate, toDate, payer);
    }

    private DataEntry create(String value, String fieldName) {

        DataEntry dataEntry = new DataEntry(fieldName, value);

        if (value == null) {

            dataEntry.setValue(EMPTY);
        }
        return dataEntry;
    }
}
