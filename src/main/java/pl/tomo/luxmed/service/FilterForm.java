package pl.tomo.luxmed.service;

import java.time.LocalDate;

public interface FilterForm {

    String getCityId();
    String getPostId();
    String getServiceId();
    LocalDate getDateFrom();
    LocalDate getToDate();
    String getDoctorId();
    String getPayerId();
    String getPayersCount();
    String getIsFromStartPage();
    String getSearchFirstFree();
    String getRequestVerificationToken();
    String getTimeOption();
    String getClinicId();
}


