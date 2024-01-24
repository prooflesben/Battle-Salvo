package cs3500.pa03.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to represent an auto AI Player
 */
public class AiPlayer extends A2Player {
  private final ArrayList<Coord> shots;
  private final StackImpl<Coord> successShots;

  /**
   * Constructor with given random for testing
   *
   * @param random random for testing
   */
  public AiPlayer(Random random) {
    super(random);
    this.shots = new ArrayList<>();
    this.successShots = new StackImpl<>();
  }

  /**
   * Constructor for true randomness
   */
  public AiPlayer() {
    this(new Random());
  }

  /**
   * Get the player's name.
   * NOTE: This may not be important to your implementation for PA03, but it will be later
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "Ben's AI Player";
  }

  /**
   * Returns this player's shots on the opponent's ABoard. The number of shots returned should
   * equal the number of ships on this player's ABoard that have not sunk.
   *
   * @return the locations of shots on the opponent's ABoard
   */
  @Override
  public List<Coord> takeShots() {
    if (successShots.isEmpty()) {
      List<Coord> shots = randomShots(numOfShots(), new ArrayList<>());
      oppBoard.takeDamage(shots, Status.MISS);
      shotLeft = shotLeft - shots.size();
      return shots;
    } else {
      List<Coord> shots = targetShots(new ArrayList<>());
      oppBoard.takeDamage(shots, Status.MISS);
      shotLeft = shotLeft - shots.size();
      return shots;
    }
  }


  /**
   * Takes shots based on an algorithm that takes into account which shots were successful and shots
   * the neighboring positions
   *
   * @param curShots the current shots
   *
   * @return a list of Coords of the shots taken
   */
  private List<Coord> targetShots(ArrayList<Coord> curShots) {
    int shotPlace = 0;
    Coord curSuccess = successShots.pop();

    while (curShots.size() < numOfShots()) {
      if (shotPlace % 4 == 0) {
        int x = curSuccess.x;
        int y = curSuccess.y - 1;
        Coord coord = new Coord(x, y);
        if (canTakeShot(coord, curShots)) {
          curShots.add(coord);
          shots.add(coord);
        }
        shotPlace += 1;
      } else if (shotPlace % 4 == 1) {
        int x = curSuccess.x + 1;
        int y = curSuccess.y;
        Coord coord = new Coord(x, y);
        if (canTakeShot(coord, curShots)) {
          curShots.add(coord);
          shots.add(coord);
        }
        shotPlace += 1;

      } else if (shotPlace % 4 == 2) {
        int x = curSuccess.x;
        int y = curSuccess.y + 1;
        Coord coord = new Coord(x, y);
        if (canTakeShot(coord, curShots)) {
          curShots.add(coord);
          shots.add(coord);
        }
        shotPlace += 1;

      } else if (shotPlace % 4 == 3) {
        int x = curSuccess.x - 1;
        int y = curSuccess.y;
        Coord coord = new Coord(x, y);
        if (canTakeShot(coord, curShots)) {
          curShots.add(coord);
          shots.add(coord);
        }
        shotPlace += 1;
        if (successShots.isEmpty()) {
          return randomShots(numOfShots() - curShots.size(), curShots);
        }

        curSuccess = successShots.pop();

      }
    }
    return curShots;
  }


  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    oppBoard.takeDamage(shotsThatHitOpponentShips, Status.HIT);

    for (Coord shots : shotsThatHitOpponentShips) {
      successShots.push(shots);
    }
  }


  /**
   * Generates a list of random shots from all the available shots on the board
   *
   * @param numShots the num shots
   * @param curShots the current shots
   * @return the list of random shots taken
   */
  public List<Coord> randomShots(int numShots, ArrayList<Coord> curShots) {
    for (int i = 0; i < numShots; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      Coord shot = new Coord(x, y);

      while (this.shots.contains(shot) || curShots.contains(shot)) {
        x = random.nextInt(width);
        y = random.nextInt(height);
        shot = new Coord(x, y);
      }

      curShots.add(shot);
      shots.add(shot);
    }

    return curShots;
  }


  /**
   * Determines if the Coord of the shot is available/can be taken
   *
   * @param shot the shot to be taken
   * @param curShots the list of current shots the ai is going to take
   *
   * @return whether the shot can be taken
   */
  private boolean canTakeShot(Coord shot, ArrayList<Coord> curShots) {
    if (shots.contains(shot)) {
      return false;
    }

    if (curShots.contains(shot)) {
      return false;
    }

    if (!myBoard.onBoard(shot)) {
      return false;
    }

    return true;
  }

}
