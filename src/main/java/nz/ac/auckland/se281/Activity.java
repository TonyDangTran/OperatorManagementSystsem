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
      String activityType,
      ActivityType activityTypeEnum,
      String operatorId,
      int activityOperatorCount) { // Activity constructor
    this.activityTypeEnum = ActivityType.fromString(activityType);
    this.activityName = activityName;
    this.activityOperatorCount = activityOperatorCount;
    this.operatorId = operatorId;
    this.activityType = activityType;
  }

  public String getActivityName() {
    return this.activityName;
  }

  public String getActivityType() {
    if (this.activityTypeEnum.toString().toUpperCase().equals(this.activityType.toUpperCase())) {
      return activityTypeEnum.toString();
    } else {
      activityTypeEnum = ActivityType.OTHER;
      return activityTypeEnum.toString();
    }
  }

  public String getOperatorID() {
    return this.operatorId;
  }

  public String getActivityID() {
    return this.operatorId + "-" + String.format("%03d", activityOperatorCount);
  }
}
