package model;


import entity.*;
import entity.Object;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * The Class DAOMap.
 *
 * @author De Grossi Hugo And Girardi Guillaume
 */
public class DAOMap {

    /** The connection. */
    private final Connection connection;

    /**
     * Instantiates a new DAOMap.
     *
     * @param connection
     * the connection
     */
    public DAOMap(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    protected Connection getConnection() {
        return this.connection;
    }

    /**
     * Set the map in the ArrayList from the DataBase
     * @param ID
     * ID of the Map
     * @return
     * return the ArrayList of MapTile
     * @throws SQLException
     * throws sql related exception
     * @throws IOException
     * throws image related exception
     */
    public ArrayList<MapTile> getMapSql(int ID) throws SQLException, IOException {
        ArrayList<MapTile> map = new ArrayList<MapTile>();
        int i = 0;

        final String sql = "{call GetMap(" + ID + ")}";
        final CallableStatement call = this.getConnection().prepareCall(sql);
        call.execute();
        final ResultSet resultSet = call.getResultSet();

        while (resultSet.next()){
            switch(resultSet.getString("components.NAME")){
                case "Player_One":
                    MapTile player_one = new MapTile(new Player_One("Player_One", false, LastMove.DOWN, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, player_one);
                    break;
                case "Enemy_One":
                    MapTile enemy_one = new MapTile(new Enemy_One("Enemy_One", false, LastMove.DOWN, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, enemy_one);
                    break;
                case "Ground_One":
                    MapTile ground_one = new MapTile(new Ground_One("Ground_One", false, LastMove.NOTHING, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, ground_one);
                    break;
                case "Ground_Two":
                    MapTile ground_two = new MapTile(new Ground_Two("Ground_Two", false, LastMove.NOTHING, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, ground_two);
                    break;
                case "Wall_One":
                    MapTile wall_one = new MapTile(new Wall_One("Wall_One", false, LastMove.NOTHING, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, wall_one);
                    break;
                case "Wall_Two":
                    MapTile wall_two = new MapTile(new Wall_Two("Wall_Two", false, LastMove.NOTHING, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, wall_two);
                    break;
                case "Rock":
                    MapTile rock = new MapTile(new Rock("Rock", false, LastMove.NOTHING, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, rock);
                    break;
                case "Diamond":
                    MapTile diamond = new MapTile(new Diamond("Diamond", false, LastMove.NOTHING, -1) ,resultSet.getInt("positions.PosX"),resultSet.getInt("positions.PosY"));
                    map.add(i, diamond);
                    break;
                default:

                    break;
            }
            i++;
        }

        return map;
    }

    /**
     * Set the size of the map from the DataBase
     * @param ID
     * ID of the Map
     * @return
     * return the size of the map
     * @throws SQLException
     * throws sql related exception
     */
    public int[] getMapSize(int ID) throws SQLException {
        int[] result = new int[2];

        final String sql = "{call GetSize(" + ID + ")}";
        final CallableStatement call = this.getConnection().prepareCall(sql);
        call.execute();
        final ResultSet resultSet = call.getResultSet();

        resultSet.first();
        result[0] = resultSet.getInt("Height");
        result[1] = resultSet.getInt("Width");

        return result;
    }

    /**
     * Set the Diamond number from the DataBase
     * @param ID
     * ID of the Map
     * @return
     * return the number of diamonds in the map
     * @throws SQLException
     * throws sql related exception
     */
    public int getDiamondNumber(int ID) throws SQLException{
        int result;

        final String sql = "{call GetDiamond(" + ID + ")}";
        final CallableStatement call = this.getConnection().prepareCall(sql);
        call.execute();
        final ResultSet resultSet = call.getResultSet();

        resultSet.first();
        result = resultSet.getInt("Diamond_Number");

        return result;
    }

}