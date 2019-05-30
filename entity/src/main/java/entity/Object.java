package entity;

public abstract class Object {
    private String name;
    private boolean status;
    private LastMove lastMove;


    public Object(String name, boolean status, LastMove lastMove){
        this.setName(name);
        this.setStatus(status);
        this.setLastMove(lastMove);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

    public LastMove getLastMove() {
        return lastMove;
    }

    public void setLastMove(LastMove lastMove) {
        this.lastMove = lastMove;
    }

}
