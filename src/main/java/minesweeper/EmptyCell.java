class EmptyCell extends Cell {

    public EmptyCell(int y, int x)
    {
        super(y, x);
    }

    public Type getType(){
        return Type.Empty;
    }
}