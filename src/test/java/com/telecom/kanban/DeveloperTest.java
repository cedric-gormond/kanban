package com.telecom.kanban;

import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.service.DeveloperService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * Performs JUnit test for developers
 *
 *
 * @author Cédric GORMOND
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeveloperTest {
    @Autowired
    private DeveloperService developerService;

    /**
     * Test if 2 {@link Developer} are presents in {@link DeveloperService}.
     *
     * They were loaded thanks to {@link com.telecom.kanban.runner.LoadDataBaseRunner} class.
     */
    @Test
    public void testFindAllDevelopers() {
        Collection<Developer> developers = this.developerService.findAllDevelopers();
        Assert.assertEquals(2, developers.size());
    }
}
