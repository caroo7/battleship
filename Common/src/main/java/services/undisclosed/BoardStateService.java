package services.undisclosed;

import models.CellState;
import models.Player;

import java.awt.*;
import java.util.Map;

public interface BoardStateService {

    Map<Point, CellState> getUserBoardState(Player player);

    Map<Point, CellState> getRivalBoardState(Player player);

}