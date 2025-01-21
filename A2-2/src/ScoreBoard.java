import Constants.Constants;

/**
 * Define how to create scoreboard for different games and show the scoreboard here.
 */
public class ScoreBoard{
    public void showScoreBoard(Maze[] mazes){
        System.out.printf(Constants.GAME_SCORE_HEADER_FORMAT, "# Game", "Player Name", "# Food Eaten", "# Monster Killed", "# Hits", "# Moves", "# Score");
        System.out.println("|========|===============|===============|================|========|========|========|");
        for (Maze maze: mazes){
            System.out.printf(Constants.GAME_SCORE_FORMAT,maze.getGameCurrentNO(), maze.getPlayerName(),
                    maze.getPacman().getEats(), maze.getPacman().getMonsterKillNo(),maze.getPacman().getHits(),
                    maze.getPacman().getMoves(), maze.calculateScore());
        }

    }
}