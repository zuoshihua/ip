public enum TaskEnum {
    TODO("T"), DEADLINE("D"), EVENT("E");

    final String symbol;
    TaskEnum(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
