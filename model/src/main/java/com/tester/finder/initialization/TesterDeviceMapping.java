package com.tester.finder.initialization;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TesterDeviceMapping {

    @CsvBindByName
    Integer testerId;

    @CsvBindByName
    Integer deviceId;
}
