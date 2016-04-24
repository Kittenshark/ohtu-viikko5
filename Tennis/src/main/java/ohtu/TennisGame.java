package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String player1Name;
    private final String player2Name;
    private final String[] gameStatus = new String[5];

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        setPointTable();
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            m_score1 += 1;
        else
            m_score2 += 1;
    }
    
    public void setPointTable(){
        gameStatus[0] = "Love";
        gameStatus[1] = "Fifteen";
        gameStatus[2] = "Thirty";
        gameStatus[3] = "Forty";
        gameStatus[4] = "Deuce";
    }

    public String getScore() {
        String score = "";
        if (m_score1==m_score2) {
            return evenGameScore(score);
        }
        if (m_score1>=4 || m_score2>=4) {
            return winGamePointScore(score);
        }
        return currentGameScore(score);
    }
    
    public String evenGameScore(String score) {
        if (m_score1 < 4) {
            return gameStatus[m_score1]+"-All";
        }
        return gameStatus[4];
    }
    
    public String winGamePointScore(String score) {
        int minusResult = m_score1-m_score2;
        if (minusResult==1) return "Advantage player1";
        if (minusResult ==-1) return "Advantage player2";
        if (minusResult>=2) return "Win for player1";
        return score ="Win for player2";
    }
    
    public String currentGameScore(String score) {
        int tempScore = 0;
        for (int i=1; i<3; i++) {
                if (i==1) tempScore = m_score1;
                else { score+="-"; tempScore = m_score2;}
                score += gameStatus[tempScore];
            }
        return score;
    }
}