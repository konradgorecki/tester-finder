package com.tester.finder.initialization;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Device {

    @CsvBindByName
    Integer deviceId;

    @CsvBindByName
    String description;
}
