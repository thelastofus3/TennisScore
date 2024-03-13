# Project Documentation: "Tennis Match Scoreboard"

## Project Goals
- Create a client-server application with a web interface.
- Gain practical experience with the ORM Hibernate.
- Design a simple web interface without third-party libraries.
- Familiarize yourself with the MVC(S) architectural pattern.

## Project Description
The project is a system for scoring a tennis match, taking into account the peculiarities of scoring in tennis. Each match is played up to two sets (best of 3), at a score of 6/6 in the set, a tie-break is played up to 7 points.

## Application Interface
### Main Page
Contains links leading to the pages of a new match and a list of completed matches.

### New Match Page
Address - /new-match. The interface consists of an HTML form with the fields "Player 1 Name", "Player 2 Name" and the "start" button. Pressing the "start" button leads to a POST request to the address /new-match.

The POST request handler performs the following actions:
- Checks the existence of players in the Players table. If a player with such a name does not exist, it creates a new one.
- Creates an instance of the Match class (containing player IDs and the current score) and puts it in the collection of current matches (existing only in the memory of the application, or in key-value storage). The key to the collection is UUID, the value is an instance of the Match class.
- Performs a redirect to the page /match-score?uuid=$match_id.

### Match Score Page
Address - /match-score?uuid=$match_id. The GET parameter uuid contains the UUID of the match.

The interface consists of a table with player names, the current score, and forms and buttons for actions - "player 1 won the current point", "player 2 won the current point". Pressing the buttons leads to a POST request to the address /match-score?uuid=$match_id, the fields of the sent form contain the ID of the player who won the point.

The POST request handler performs the following actions:
- Extracts an instance of the Match class from the collection.
- Depending on which player won the point, updates the match score.
- If the match is not over - renders the match score table with the buttons described above.
- If the match is over:
  - Removes the match from the collection of current matches.
  - Records the completed match in the SQL database.
  - Renders the final score.

### Played Matches Page
Address - /matches?page=$page_number&filter_by_player_name=$player_name. GET parameters:
- page - page number. If the parameter is not specified, the first page is assumed.
- filter_by_player_name - the name of the player whose matches we are looking for. If the parameter is not specified, all matches are displayed.

Displays a list of played matches page by page. Allows you to search for a player's matches by his name. For page-by-page display, pagination implementation will be required.

The interface consists of a form with a filter by player name. Input field for the name and the "search" button. When pressed, a GET request of the form /matches?filter_by_player_name=${NAME} is formed. Also displays a list of found matches and a page switcher, if more matches are found than fit on one page.

## Database Structure
### Players Table - players
| Column Name | Type | Comment |
| --- | --- | --- |
| ID | Int | Primary key, autoincrement |
| Name | Varchar | Player name |

Indexes:
- Index of the Name column, for efficient player search by name.

### Matches Table - completed matches
For simplicity, only completed matches are saved in the DB at the moment of their completion.

| Column Name | Type | Comment |
| --- | --- | --- |
| ID | Int | Primary key, autoincrement |
| Player1 | Int | ID of the first player, foreign key to Players.ID |
| Player2 | Int | ID of the second player, foreign key to Players.ID |
| Winner | Int | ID of the winner, foreign key to Players.ID |

## MVCS
MVCS is an architectural pattern that is particularly well suited for the implementation of such applications. Below I will only give an example of how it can be used in this project.

### Match Score Accounting

MatchScoreController:
- Handles POST requests to /match-score
- Through OngoingMatchesService, it gets an instance of the Match class for the current match, which is a model of the Match model.
- Through MatchScoreCalculationService, it updates the score in the match.
- If the match is over - through FinishedMatchesPersistenceService, it saves the completed match in the database.

Each of the mentioned services does a specific job:
- OngoingMatchesService stores current matches and allows them to be written/read.
- MatchScoreCalculationService implements the logic of scoring a match by points/games/sets.
- FinishedMatchesPersistenceService encapsulates reading and writing of completed matches in the DB.

### Tests
We will cover the scoring in the match with unit tests. Examples of cases:
- If player 1 wins a point at a score of 40-40, the game does not end.
- If player 1 wins a point at a score of 40-0, then he wins the game.
- At a score of 6-6, a tie-break starts instead of a regular game.
