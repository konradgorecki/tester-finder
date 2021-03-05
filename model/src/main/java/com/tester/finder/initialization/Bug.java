package com.tester.finder.initialization;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bug {

    @CsvBindByName
    Integer bugId;

    @CsvBindByName
    Integer deviceId;

    @CsvBindByName
    Integer testerId;
}
