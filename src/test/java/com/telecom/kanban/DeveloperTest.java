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

@SpringBootTest
@RunWith(SpringRunner.class)
/**
 * Performs JUnit test for developers
 */
public class DeveloperTest {
    @Autowired
    private DeveloperService developerService;

    @Test
    public void testFindAllDevelopers() {
        Collection<Developer> developers = this.developerService.findAllDevelopers();
        Assert.assertEquals(2, developers.size());
    }
}
