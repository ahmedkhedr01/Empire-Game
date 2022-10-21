package engine;

public interface GameListener {
 void onGame(Player p);
 void onTargetCity();
 void onEndTurn();
 void onAutoResolve();
 void onOccupy(City city);
 void onLoadCitiesAndDistances();
 void onLoadArmy();

}
