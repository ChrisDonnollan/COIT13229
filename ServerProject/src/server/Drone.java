package server;
import java.io.Serializable;
public class Drone implements Serializable
 {
	private String droneID;
	private String droneName;
	private String positionX;
	private String positionY;
        //serializable classes should have a serialVersionUID
        private static long serialVersionUID = 3456728L;
	
	 public Drone() {
	     
	 }

    public Drone(String droneID, String droneName, String positionX, String positionY) {
        this.droneID = droneID;
        this.droneName = droneName;
        this.positionX = positionX;
        this.positionY = positionY;
    }
         
        public Drone (Drone another) {
             this(another.droneID,another.droneName, another.positionX, 
                     another.positionY);
         }

    public String getDroneID() {
        return droneID;
    }

    public void setDroneID(String droneID) {
        this.droneID = droneID;
    }

    public String getDroneName() {
        return droneName;
    }

    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    
        @Override 
        public String  toString() //getDroneDetails () 
       {
           return String.format(" Drone ID: %s  Drone Name: %s   \n Position X: %s  "
                   + "Position Y: %s ", this.droneID, 
                   this.droneName,this.positionX,this.positionY);
        }

//    @Override
//    public String toString() {
//        return "Drone{" + "droneID=" + droneID + ", droneName=" + droneName + ", positionX=" + positionX + ", positionY=" + positionY + '}';
//    }

  }

