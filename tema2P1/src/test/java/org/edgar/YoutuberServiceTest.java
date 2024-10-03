package org.edgar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@ExtendWith(MockitoExtension.class)
public class YoutuberServiceTest {
@Test
public void testYoutuberMoreFollowers_WithMultipleYoutubers_ReturnsYoutuberWithMostFollowers() {
    // Setup
    Youtuber youtuber1 = new Youtuber("Youtuber1", LocalDate.now(), 1000, 100);
    Youtuber youtuber2 = new Youtuber("Youtuber2", LocalDate.now(), 5000, 500);
    Youtuber youtuber3 = new Youtuber("Youtuber3", LocalDate.now(), 3000, 300);
    List<Youtuber> youtubers = Arrays.asList(youtuber1, youtuber2, youtuber3);
    YoutuberService service = new YoutuberService();
    ReflectionTestUtils.setField(service, "youtubers", youtubers);
    // Execution
    Youtuber result = service.youtuberMoreFollowers();
    // Verification
    assertEquals(youtuber2, result);
    }
@Test
public void testYoutuberMoreFollowers_WithSingleYoutuber_ReturnsSameYoutuber() {
    // Setup
    Youtuber youtuber1 = new Youtuber("Youtuber1", LocalDate.now(), 1000, 100);
    List<Youtuber> youtubers = Collections.singletonList(youtuber1);
    YoutuberService service = new YoutuberService();
    ReflectionTestUtils.setField(service, "youtubers", youtubers);
    // Execution
    Youtuber result = service.youtuberMoreFollowers();
    // Verification
    assertEquals(youtuber1, result);
    }
}