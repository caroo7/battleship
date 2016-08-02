package services.shared;

import gameLogic.CellState;

import java.awt.*;
import java.util.Map;

public interface ShootService {

    Map<Point, CellState> shootOn(Point point);

}
