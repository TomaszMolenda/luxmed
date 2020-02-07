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

        create(filterForm.getDateOption(), "DateOption"),
        create(filterForm.getFilterType(), "FilterType"),
        create(storage.getCoordinationActivityId(), "CoordinationActivityId"),
        create(filterForm.getIsFFS(), "IsFFS"),
        create(filterForm.getMaxPeriodLength(), "MaxPeriodLength"),
        create(filterForm.getIsDisabled(), "IsDisabled"),
        create(filterForm.getPayersCount(), "PayersCount"),
        create(filterForm.getFromDate(), "FromDate"),
        create(filterForm.getToDate(), "ToDate"),
        create(filterForm.getDefaultSearchPeriod(), "DefaultSearchPeriod"),
        create(filterForm.getCustomRangeSelected(), "CustomRangeSelected"),
        create(filterForm.getSelectedSearchPeriod(), "SelectedSearchPeriod"),
        create(filterForm.getCityId(), "CityId"),
        create(filterForm.getClinicId(), "ClinicId"),
        create(filterForm.getDateRangePickerButtonDefaultLabel(), "DateRangePickerButtonDefaultLabel"),
        create(filterForm.getServiceId(), "ServiceId"),
        create(filterForm.getTimeOption(), "TimeOption"),
        create(filterForm.getPayerId(), "PayerId"),
        create(filterForm.getLanguageId(), "LanguageId")
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
