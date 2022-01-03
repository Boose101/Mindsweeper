class BombCell extends Cell {

    public BombCell(int y, int x)
    {
        super(y, x);
    }

    public Type getType(){
        return Type.Bomb;
    }
}