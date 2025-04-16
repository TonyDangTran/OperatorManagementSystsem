package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;

public class Activity {
  private ActivityType activityTypeEnum;
  private String activityName;
  private String activityType;
  private String operatorId;
  private int activityOperatorCount;

  Activity(
      String activityName,
      ActivityType activityTypeEnum,
      String operatorId,
      int activityOperatorCount) {
    this.activityName = activityName;
    this.activityTypeEnum = activityTypeEnum;
    this.activityType = activityTypeEnum.getName();
    this.activityOperatorCount = activityOperatorCount;
    this.operatorId = operatorId;
  }

  public String getActivityName() {
    return this.activityName;
  }

  public String getActivityType() {
    return this.activityType;
  }

  public String getOperatorID() {
    return this.operatorId + "-" + String.format("%03d", activityOperatorCount);
  }
}
