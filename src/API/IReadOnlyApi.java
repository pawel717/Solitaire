package API;

import java.awt.Point;
import java.util.List;

import Model.Card;

public interface IReadOnlyApi 
{
	List<Point> readStacksHooks();
	List<Card> readCards();
	String getTickCount();
	int getScore();
	List<String> readScores();
	List<String> readUsernames();
}