package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore=0;
        if (m_score1==m_score2)
        {
            score = tasaspelitilanne(score);
        }
        else if (m_score1>=4 || m_score2>=4)
        {
            score = pistetilanneYliNeljan(score);
        }
        else
        {   
            for (int i=1; i<3; i++)
            {
                if (i==1) tempScore = m_score1;
                else { score+="-"; tempScore = m_score2;}
                
                score = tilannepalaute(tempScore, score);
            }
        }
        return score;
    }
    
    public String tasaspelitilanne(String score) {
        switch (m_score1)
            {
                case 0:
                        return "Love-All";
                case 1:
                        return "Fifteen-All";
                case 2:
                        return "Thirty-All";
                case 3:
                        return "Forty-All";
                default:
                        return "Deuce";          
            }
    }
    
    public String pistetilanneYliNeljan(String score) {
        int minusResult = m_score1-m_score2;
        if (minusResult==1) return "Advantage player1";
        if (minusResult ==-1) return "Advantage player2";
        if (minusResult>=2) return "Win for player1";
        return score ="Win for player2";
    }
    
    public String muuTilanne(String score){
        int tempScore=0;
        for (int i=1; i<3; i++)
            {
                if (i==1) tempScore = m_score1;
                else { score+="-"; tempScore = m_score2;}
                switch(tempScore)
                {
                    case 0:
                        score += "Love";
                        //return score+"Love";
                        return score;
                    case 1:
                        return score+"Fifteen";
                    case 2:
                        return score+"Thirty";
                    case 3:
                        return score+"Forty";
                }
            }
        return score;
    }
    
    public String tilannepalaute(int tempScore, String score) {
        switch(tempScore)
                {
                    case 0:
                        return score+"Love";
                    case 1:
                        return score+"Fifteen";
                    case 2:
                        return score+"Thirty";
                    case 3:
                        return score+"Forty";
        }
        return score;
    }
}