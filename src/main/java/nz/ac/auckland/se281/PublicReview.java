package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private String name;
  private String anonymous;
  private String reviewText;
  private int rating;
  private String reviewID;
  private int reviewCount;

  public PublicReview(
      String name,
      String anonymous,
      int rating,
      String reviewText,
      String reviewID,
      int reviewCount) {
    super(name, reviewText, rating, reviewID, reviewCount);
    this.name = name;
    this.reviewText = reviewText;
    this.rating = rating;
  }

  public boolean getAnonymous() {
    if (this.anonymous.equals("y")) {
      return true;
    } else {
      return false;
    }
  }
}

// Requires:
// Option[0] = Name
// Option[1] = y/n anonymous
// Option[2] = 1-5 stars
// Option[3] = Review text
