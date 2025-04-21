package nz.ac.auckland.se281;

public abstract class Review {

  private String reviewText;
  private int rating;
  private String reviewId;
  private int reviewCount;

  private String type;

  public Review( // public review constructor
      String name,
      String anonymous,
      int rating,
      String reviewText,
      String reviewID,
      int reviewCount) {

    this.rating = rating;
    this.reviewText = reviewText;
    this.reviewCount = reviewCount;
    this.type = "Public";
  }

  public Review( // private review constructor
      String name,
      String email,
      int rating,
      String reviewText,
      String followUp,
      String reviewID,
      int reviewCount) {

    this.rating = rating;
    this.reviewText = reviewText;
    this.reviewCount = reviewCount;
    this.type = "Private";
  }

  public Review( // expert review
      String name,
      int rating,
      String reviewText,
      String recommendation,
      String reviewId,
      int reviewCount) {

    this.rating = rating;
    this.reviewText = reviewText;
    this.reviewId = reviewId;
    this.reviewCount = reviewCount;
    this.type = "Expert";
  }

  public String getReviewText() {
    return this.reviewText;
  }

  public int getRating() {
    if (this.rating < 1) {
      this.rating = 1;
    } else if (this.rating > 5) {
      this.rating = 5;
    }
    return this.rating;
  }

  public String getActivityId() {
    return this.reviewId;
  }

  public String getReviewId() {
    return this.reviewId + "-R" + reviewCount;
  }

  public abstract String getName();

  public String getType() {
    return this.type;
  }
}
