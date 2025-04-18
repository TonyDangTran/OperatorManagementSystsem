package nz.ac.auckland.se281;

public abstract class Review {
  private String name;
  private String reviewText;
  private int rating;
  private String reviewID;
  private int reviewCount;
  private String anonymous;
  private String email;
  private String followUp;
  private String recommendation;
  private String type;

  // public Review(String name, String reviewText, int rating, String reviewID, int reviewCount) {
  //   this.name = name;
  //   this.reviewText = reviewText;
  //   this.rating = rating;
  //   this.reviewID = reviewID;
  //   this.reviewCount = reviewCount;
  // }

  public Review( // public review constructor
      String name,
      String anonymous,
      int rating,
      String reviewText,
      String reviewID,
      int reviewCount) {

    this.name = name;
    this.anonymous = anonymous;
    this.rating = rating;
    this.reviewText = reviewText;
    this.reviewID = reviewID;
    this.reviewCount = reviewCount;
    this.type = "Public";
  }

  public Review( // private
      String name,
      String email,
      int rating,
      String reviewText,
      String followUp,
      String reviewID,
      int reviewCount) {
    this.name = name;
    this.email = email;
    this.rating = rating;
    this.reviewText = reviewText;
    this.followUp = followUp;
    this.reviewID = reviewID;
    this.reviewCount = reviewCount;
    this.type = "Private";
  }

  public Review( // expert
      String name,
      int rating,
      String reviewText,
      String recommendation,
      String reviewID,
      int reviewCount) {
    this.name = name;
    this.rating = rating;
    this.reviewText = reviewText;
    this.recommendation = recommendation;
    this.reviewID = reviewID;
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

  public String getActivityID() {
    return this.reviewID;
  }

  public String getReviewID() {
    return this.reviewID + "-R" + reviewCount;
  }

  public abstract String getName();

  public String getType() {
    return this.type;
  }
}
