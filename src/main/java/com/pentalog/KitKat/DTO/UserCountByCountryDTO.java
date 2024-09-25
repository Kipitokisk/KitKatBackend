package com.pentalog.KitKat.DTO;

public class UserCountByCountryDTO {
    private String countryName;
    private long userCount;

    public UserCountByCountryDTO(String countryName, long userCount) {
        this.countryName = countryName;
        this.userCount = userCount;
    }

    public String getCountryName() {
        return countryName;
    }

    public long getUserCount() {
        return userCount;
    }
}
