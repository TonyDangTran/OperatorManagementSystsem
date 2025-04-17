package nz.ac.auckland.se281;

public abstract class Review {
  private String name;
  private String reviewText;
  private int rating;
  private String reviewID;
  private int reviewCount;

  public Review(String name, String reviewText, int rating, String reviewID, int reviewCount) {
    this.name = name;
    this.reviewText = reviewText;
    this.rating = rating;
    this.reviewID = reviewID;
    this.reviewCount = reviewCount;
  }

  public String getName() {
    return this.name;
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

  public String getActivityID() {
    return this.reviewID;
  }

  public String getReviewID() {
    return this.reviewID + "-R" + reviewCount;
  }
}
