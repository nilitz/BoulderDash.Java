package entity;
/**
 * The class  Object
 * @author HugoDegrossi
 */
public abstract class Object {
    private String name;
    private boolean status;
    private LastMove lastMove;

    /**
     * Construcot
     * @param name
     * @param status
     * @param lastMove
     */
    public Object(String name, boolean status, LastMove lastMove){
        this.setName(name);
        this.setStatus(status);
        this.setLastMove(lastMove);
    }

    /**
     * Getter of Name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of Name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter of Status
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Getter of Status.
     * @return
     */
    public boolean getStatus(){
        return status;
    }

    /**
     * Getter Last Move.
     * @return
     */
    public LastMove getLastMove() {
        return lastMove;
    }

    /**
     * Setter Last Move.
     * @param lastMove
     */
    public void setLastMove(LastMove lastMove) {
        this.lastMove = lastMove;
    }

}
