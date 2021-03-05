package com.tester.finder.initialization;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Tester {

    @CsvBindByName
    private Integer testerId;

    @CsvBindByName
    String firstName;

    @CsvBindByName
    String lastName;

    @CsvBindByName
    String country;

    @CsvBindByName
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    LocalDateTime lastLogin;
}

