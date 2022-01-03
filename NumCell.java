class NumCell extends Cell {

    public NumCell(int y, int x, int number)
    {
        super(y, x);
        this.num = number;
    }

    public int getNum()  { return num;  }

    public Type getType(){
        return Type.Num;
    }
    private int num;
}