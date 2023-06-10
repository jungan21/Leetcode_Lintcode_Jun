package company.walmart.design.elevator;

public class ExternalRequest extends Request{

    private Direction direction;

    public ExternalRequest(int l, Direction d) {
        super(l);
        this.direction = d;
    }

    public Direction getDirection() {
        return direction;
    }
}
