package pl.bajtas.whoshouldcall.service.seeder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bajtas on 23.05.2017.
 */
@Component
public class DataSeeder {
    private static final Logger LOG = Logger.getLogger(DataSeeder.class);

    @Autowired List<DbSeeder> dbSeeders;

    public void seed() {
        LOG.info("Seeding data started...");

        for (DbSeeder dbSeeder : dbSeeders) {
            dbSeeder.seed();
        }

        LOG.info("Data initialized successfully!");
    }
}
