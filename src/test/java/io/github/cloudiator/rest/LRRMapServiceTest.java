package io.github.cloudiator.rest;

import io.github.cloudiator.rest.model.LRRStatus;
import io.github.cloudiator.rest.model.LRRType;
import io.github.cloudiator.rest.model.LongRunningRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;

public class LRRMapServiceTest {

  private final LRRMapService lrrMapService;
  private final LongRunningRequest testlrr1;
  private final LongRunningRequest testlrr2;

  @MockBean
  private UserServiceOld mockuserservice;

  public LRRMapServiceTest() {
    this.lrrMapService = new LRRMapService();
    this.testlrr1 = new LongRunningRequest()
        .id("32chars_long_testID_for_testlrr1")
        .lrrData("lrr1_TestData")
        .lrrDiagnostic("lrr1_DiagnosticText")
        .referenceId("32chars_long_testID_for_testlrr2")
        .taskStatus(LRRStatus.RUNNING).taskType(LRRType.VIRTUALMACHINEREQUEST);
    this.testlrr2 = new LongRunningRequest()
        .id("32chars_long_testID_for_testlrr2")
        .lrrData("lrr2_TestData")
        .lrrDiagnostic("lrr2_DianosticText")
        .referenceId("32chars_long_testID_for_testlrr1").taskStatus(LRRStatus.RUNNING)
        .taskType(LRRType.VIRTUALMACHINEREQUEST);

  }

  @Test
  public void getLRR() throws Exception {
    lrrMapService.addLRR("DummyUser", testlrr1);

    LongRunningRequest actual = lrrMapService
        .getLRR("DummyUser", "32chars_long_testID_for_testlrr1");

    assertThat(actual, is(equalTo(testlrr1)));

  }

  @Test
  public void addLRR() throws Exception {
    LongRunningRequest actual = lrrMapService.addLRR("DummyUser", testlrr1);

    assertThat(actual, is(equalTo(testlrr1)));
  }

  @Test
  public void getAllLRR() throws Exception {
    lrrMapService.addLRR("DummyUser", testlrr1);
    lrrMapService.addLRR("DummyUser", testlrr2);

    List<LongRunningRequest> testlist = new ArrayList<>();
    testlist = lrrMapService.getAllLRR("DummyUser");

    assertThat(testlist.size(), is(2));
    assertThat(testlist.get(0), is(equalTo(testlrr1)));
    assertThat(testlist.get(1), is(equalTo(testlrr2)));
  }

  @Test
  public void changeLRRStatus() throws Exception {
    lrrMapService.addLRR("DummyUser", testlrr1);

    assertThat(
        lrrMapService.getLRR("DummyUser", "32chars_long_testID_for_testlrr1").getTaskStatus(),
        is(LRRStatus.RUNNING));

    LongRunningRequest actual = lrrMapService
        .changeLRRStatus("DummyUser", "32chars_long_testID_for_testlrr1", LRRStatus.COMPLETED);

    assertThat(actual.getTaskStatus(), is(LRRStatus.COMPLETED));

  }

  @Test(expected = IllegalArgumentException.class)
  public void checkUserId_isEmpty() {
    LongRunningRequest testlrr3 = new LongRunningRequest().id("");

    lrrMapService.addLRR("", testlrr3);
    lrrMapService.addLRR("DummyUser", testlrr3);

  }

  @Test(expected = NullPointerException.class)
  public void checkLrrId_isNull() {
    LongRunningRequest testlrr3 = new LongRunningRequest();

    lrrMapService.addLRR("DummyUser", testlrr3);

  }

}