package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private String name;
  private String anonymous;
  private boolean endorsement;

  public PublicReview(
      String name,
      String anonymous,
      int rating,
      String reviewText,
      String reviewID,
      int reviewCount) {
    super(name, anonymous, rating, reviewText, reviewID, reviewCount);
    this.name = name;
    this.anonymous = anonymous;
    this.endorsement = false;
  }

  public boolean getAnonymous() {
    if (this.anonymous.equals("y")) {
      return true;
    } else {
      return false;
    }
  }

  public boolean setEndorsement(boolean Endorsement) {
    return this.endorsement = Endorsement;
  }

  public boolean getEndorsement() {
    return this.endorsement;
  }

  @Override
  public String getName() {
    if (getAnonymous()) {
      return "Anonymous";
    } else {
      return this.name;
    }
  }
}

// Requires:
// Option[0] = Name
// Option[1] = y/n anonymous
// Option[2] = 1-5 stars
// Option[3] = Review text
