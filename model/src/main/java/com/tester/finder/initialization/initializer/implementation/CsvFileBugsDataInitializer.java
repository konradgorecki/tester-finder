package com.tester.finder.initialization.initializer.implementation;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tester.finder.core.Device;
import com.tester.finder.core.Tester;
import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.initialization.Bug;
import com.tester.finder.initialization.initializer.BugsDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static com.tester.finder.initialization.initializer.implementation.CsvBeanFilters.SKIP_EMPTY_LINES;

@AllArgsConstructor
public class CsvFileBugsDataInitializer implements BugsDataInitializer {

    private final BugsRepository bugsRepository;
    private final TestersRepository testersRepository;
    private final DevicesRepository devicesRepository;

    @Override
    public void initFromFile(File file) {
        List<Bug> bugs;
        try {
            bugs = new CsvToBeanBuilder<Bug>(new FileReader(file))
                    .withType(Bug.class)
                    .withFilter(SKIP_EMPTY_LINES).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(String.format("Bugs File not found! %s", file.getAbsolutePath()));
        }
        if (bugs == null) {
            return;
        }
        bugs.forEach(this::persist);
    }

    private void persist(Bug source) {
        Tester tester = testersRepository.findById(source.getTesterId());
        Device device = devicesRepository.findById(source.getDeviceId());
        bugsRepository.save(new com.tester.finder.core.Bug(source.getBugId(), device, tester));
    }
}
