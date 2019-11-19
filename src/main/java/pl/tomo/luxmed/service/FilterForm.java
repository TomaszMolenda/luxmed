package pl.tomo.luxmed.service;

import java.time.LocalDate;

public interface FilterForm {

    int days = 14;
    LocalDate now = LocalDate.now();

    default String getDateOption() {
        return "SelectedDate";
    }

    default String getFilterType() {
        return "Coordination";
    }

    default String getCoordinationActivityId() {
        return "96";
    }

    default String getIsFFS() {
        return "False";
    }

    default String getMaxPeriodLength() {
        return "0";
    }

    default String getIsDisabled() {
        return "False";
    }

    default String getPayersCount() {
        return "0";
    }

    default LocalDate getFromDate() {
        return now;
    }

    default LocalDate getToDate() {
        return now.plusDays(days);
    }

    default String getDefaultSearchPeriod() {
        return String.valueOf(days);
    }

    default String getCustomRangeSelected() {
        return "False";
    }

    default String getSelectedSearchPeriod() {
        return String.valueOf(days);
    }

    String getCityId();
    String getClinicId();

    default String getDateRangePickerButtonDefaultLabel() {
        return "Inny zakres";
    }

    String getServiceId();

    default String getTimeOption() {
        return "0";
    }

    String getPayerId();

    default String getLanguageId() {
        return "";
    }
}


