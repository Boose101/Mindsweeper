public abstract class Cell
{
public enum State {
        Clicked,
        Flagged,
        None
};

public enum Type {
        Bomb,
        Num,
        Empty
};

public Cell(int y, int x)
{
        _x = x;
        _y = y;
        _state = State.None;
}

public abstract Type getType();

public int getX()           {
        return _x;
}
public int getY()           {
        return _y;
}

public boolean clicked()    {
        return _state == State.Clicked;
}
public boolean flagged()    {
        return _state == State.Flagged;
}

public void setClicked(boolean v) {
        if (_state == State.None) {
                _state = State.Clicked;
        }
}

public void setFlagged(boolean v) {
        if (v && _state == State.None)
        {
                _state = State.Flagged;
        }
        else if (!v && _state == State.Flagged)
        {
                _state = State.None;
        }
}

private State _state;
private int _x;
private int _y;
}
